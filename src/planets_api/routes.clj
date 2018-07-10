(ns planets-api.routes
    (:require [compojure.route :as route]
              [compojure.core :refer [defroutes POST GET]]
              [planets-api.controller :as controller]))

(defroutes app-routes
    (POST "/planets" {body :body} (controller/create-planet body))
    (GET "/planets" [] (controller/get-list-planets))
    (route/not-found "Not Found"))