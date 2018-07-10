(ns planets-api.requests.not-found-test
    (:require [clojure.test :refer :all]
              [planets-api.helpers.utils :refer [make-request]]))

(deftest get-not-found-test
    (testing "test post to jobs endpoint with wrong json body."
        (let [response (make-request :post "/foobar")]
            (is (= (:status response) 404)))))
              