(ns test-planets-api.helpers.requests
    (:require [ring.mock.request :as req-mock]
              [planets-api.main :refer [app]]))

(defn make-request
  "Helpper to make some requests (with or without body)."
  ([request-type endpoint]
    (app (req-mock/request request-type endpoint)))
  ([request-type endpoint body]
    (app (req-mock/json-body (req-mock/request request-type endpoint) body))))