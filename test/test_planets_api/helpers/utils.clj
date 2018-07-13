(ns test-planets-api.helpers.utils
    (:require [ring.mock.request :as req-mock]
              [planets-api.main :refer [app]]))

(defn make-request
  "Helpper to make some requests (with or without body)."
  ([request-type endpoint]
    (app (req-mock/request request-type endpoint)))
  ([request-type endpoint body]
    (app (req-mock/json-body (req-mock/request request-type endpoint) body))))

(defn submap? [map-A map-B]
  "Check if map-A is a subset of map-B"
  (clojure.set/subset? (set map-A) (set map-B)))