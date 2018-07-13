(ns planets-api.e2e.not-found-test
    (:require [clojure.test :refer :all]
              [planets-api.helpers.utils :refer [make-request]]))

(deftest post-create-planet
    (testing "test create a valid planet."
        (let [response (make-request :post "/planets")]
            (is (= (:status response) 404)))))