(ns planets-api.database
  (:require monger.json
            [monger.db :refer [drop-db]]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :as query]
            [monger.operators :refer [$in $set]]))

(def db (mg/get-db (mg/connect) "planets-api"))

(defn create-db
  "Create index in and unique keys in name on planet doc."
  []
  (mc/create-index db "planets" ["name"] {:unique true}))

(defn destroy-db
  "Destroy database."
  []
  (drop-db db))
    
(defn clean-db
  "Remove all documents from database."
  []
  (mc/remove db "planets"))

(defn insert-planet
  "Insert a planet in db."
  [planet]
  (mc/insert db "planets" planet))

(defn get-all-planets
  "Get all planets from db."
  []
  (mc/find-maps db "planets"))

(defn get-planets-by-name
  "Get planets by name"
  [name]
  (query/with-collection db "planets"
    (query/find {:name name})))