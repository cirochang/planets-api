
(ns planets-api.models.planet-md
  (:require [schema.core :refer [required-key Str]]
            [cheshire.core :refer [parse-string]]
            [planets-api.utils.database :as db]
            [planets-api.utils.swapi :as swapi]))


(defn- get-total-films-by-planet
  "Get the number of films of an planet by swapi."
  [planet]
  (let [response (swapi/get-planet-info planet)
        body (parse-string (get-in response [:body]))
        results (get-in body ["results"])
        planet (first results)
        films (get-in planet ["films"])]
    (count films)))

(def schema
  "The planet schema."
  {(required-key "name") Str
   (required-key "climate") Str
   (required-key "terrain") Str})

(defn create-planet
  "Add num films in planet and insert in db."
  [planet]
  (let [num-films (get-total-films-by-planet planet)
        planet-with-films (merge planet {:num_films num-films})]
    (db/insert-doc "planets" planet-with-films)))

(defn get-all-planets
  "Get all planets from db"
  []
  (db/get-all-docs "planets"))

(defn get-planet-by-id
  "Get planet by id from db"
  [planet-id]
  (db/get-doc-by-id "planets" planet-id))

(defn get-planets-by-name
  "Get planet by name from db"
  [name]
  (db/find-docs "planets" {:name name}))

(defn remove-planet-by-id
  "Remove planet from db by id"
  [planet-id]
  (db/remove-doc-by-id "planets" planet-id))