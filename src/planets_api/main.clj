(ns planets-api.main
  (:require [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [planets-api.routes :as routes]
            [planets-api.utils.database :as db]))


(db/create-db)

(def app
  (-> (handler/api routes/app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-response)))