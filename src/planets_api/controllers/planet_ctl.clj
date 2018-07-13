(ns planets-api.controllers.planet-ctl
  (:require [ring.util.response :refer [response status]]
            [schema.core :refer [validate]]
            [planets-api.models.planet-md :as planet-md]))

(defn create-planet
  "Add a planet in the database"
  [planet]
  (try
    (do
      (validate planet-md/schema planet)
      (planet-md/create-planet planet)
      (response "ok"))
    (catch com.mongodb.DuplicateKeyException e
      (status (response "This planet already exists in database") 409))
    (catch java.lang.RuntimeException e
      (status (response (str (.getMessage e))) 400))))

(defn get-list-planets
  "Return a list of planets."
  [name]
  (let [planets (if (clojure.string/blank? name)
          (planet-md/get-all-planets)
          (planet-md/get-planets-by-name name))]
    (response {:planets planets})))

(defn get-planet-by-id
  "Return the given planet by id."
  [planet-id]
  (try
    (let [planet (planet-md/get-planet-by-id planet-id)]
      (response planet))
    (catch java.lang.IllegalArgumentException e
      (status (response "Not Found") 404))))

(defn remove-planet-by-id
  "Remove the given planet by id."
  [planet-id]
  (try
    (do
      (planet-md/get-planet-by-id planet-id)
      (planet-md/remove-planet-by-id planet-id)
      (response "ok"))
    (catch java.lang.IllegalArgumentException e
      (status (response "Not Found") 404))))