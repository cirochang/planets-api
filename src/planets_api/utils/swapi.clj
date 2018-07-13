(ns planets-api.utils.swapi
  (:require [clj-http.client :as client]))


(def url "https://swapi.co/api/")

(defn get-planet-info
  "Make a request to swapi to get info about planet."
  [planet]
  (let [planet-name (get planet "name")
        planet-info-url (str url "planets?format=json&search=" planet-name)]
    (client/get planet-info-url)))

