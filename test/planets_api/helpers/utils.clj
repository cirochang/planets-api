(ns planets-api.helpers.utils
    (:require [ring.mock.request :as req-mock]
              [planets-api.main :refer [app]]))
  
(defn make-request
    "Helpper to make some requests."
    ([request-type endpoint]
      (app (req-mock/request request-type endpoint)))
    ([request-type endpoint body]
      (app (req-mock/json-body (req-mock/request request-type endpoint) body))))