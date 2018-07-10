(ns planets-api.routes
    (:require [compojure.route :as route]
              [compojure.core :refer [defroutes POST GET]]
              [planets-api.controller :as controller]))

(defroutes app-routes
    (POST "/planets" {body :body} (controller/create-planet body))
    (GET "/planets" [search] (controller/get-list-planets search))
    (GET "/planets/:planet-id" [planet-id] (controller/get-planet-by-id planet-id))
    (route/not-found "Not Found"))