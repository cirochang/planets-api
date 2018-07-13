(ns test-planets-api.e2e.not-found-test
  (:require [clojure.test :refer :all]
            [test-planets-api.helpers.utils :refer [make-request]]))

(deftest endpoint-not-found-test
  (testing "test a endpoint that not exists."
    (let [response (make-request :get "/foobar")]
      (is (= (:status response) 404)))))
