(ns planets-api.routes
  (:require [compojure.route :as route]
            [compojure.core :refer [defroutes POST GET DELETE]]
            [planets-api.controllers.planet-ctl :as planet-ctl]))

(defroutes app-routes
  (POST "/planets" {body :body} (planet-ctl/create-planet body))
  (GET "/planets" [search] (planet-ctl/get-list-planets search))
  (GET "/planets/:planet-id" [planet-id] (planet-ctl/get-planet-by-id planet-id))
  (DELETE "/planets/:planet-id" [planet-id] (planet-ctl/remove-planet-by-id planet-id))
  (route/not-found "Not Found"))