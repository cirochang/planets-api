(ns planets-api.controller
    (:require [ring.util.response :refer [response status]]
              [schema.core :refer [validate]]
              [planets-api.schema :as schema]
              [planets-api.database :as database]
              [clj-http.client :as client]
              [cheshire.core :refer [parse-string]]))

(defn- get-total-films-by-planet
  "Make a request to https://swapi.co/api and get the number of films of planet"
  [planet]
  (let [planet-name (:name planet)
        url (str "https://swapi.co/api/planets?format=json&search=" planet-name)
        response (client/get url)
        body (parse-string (get-in response [:body]))
        results (get-in body ["results"])
        planet (first results)
        films (get-in planet ["films"])]
    (count films)))

(defn create-planet
  "Add a planet in the database"
  [planet]
  (try
    (do
      (validate schema/planet planet)
      (let [num-films (get-total-films-by-planet planet)
            planet-with-films (merge planet {:num_films num-films})] 
        (database/insert-planet planet-with-films))
      (response "ok"))
    (catch com.mongodb.DuplicateKeyException e
      (status (response "This job already exists in database") 409))
    (catch java.lang.RuntimeException e
      (status (response (str (.getMessage e))) 400))))

(defn get-list-planets
  "Return a list of planets."
  [search]
  (let [planets (if (clojure.string/blank? search)
          (database/get-all-planets)
          (database/get-planets-by-name search))]
    (response planets)))