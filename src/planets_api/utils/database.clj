(ns planets-api.utils.database
  (:require monger.json
            [monger.db :refer [drop-db]]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :as query]
            [monger.operators :refer [$in $set]])
  (:import org.bson.types.ObjectId))
  
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
  
(defn insert-doc
  "Insert a document in db."
  [model doc]
  (mc/insert db model doc))
  
(defn get-all-docs
  "Get all planets from db."
  [model]
  (mc/find-maps db model))
  
(defn find-docs
  "Find docs by query dict"
  [model find-query]
  (query/with-collection db model
    (query/find find-query)))
  
(defn get-doc-by-id
  "Get a doc by id"
  [model id]
  (mc/find-map-by-id db model (ObjectId. id)))
  
(defn remove-doc-by-id
  "Remove a doc by id"
  [model id]
  (mc/remove-by-id db model id))