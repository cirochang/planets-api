(ns planets-api.routes
    (:require [compojure.route :as route]
              [compojure.core :refer [defroutes]]))

(defroutes app-routes
    (route/not-found "Not Found"))