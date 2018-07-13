(ns test-planets-api.e2e.planets_test
  (:require [clojure.test :refer :all]
            [test-planets-api.helpers.fixtures :refer [tmp-db-init tmp-db-clean]]
            [test-planets-api.helpers.requests :refer [make-request]]
            [test-planets-api.helpers.factories :as factory]
            [cheshire.core :refer [generate-string parse-string]]))

(use-fixtures :once tmp-db-init)
(use-fixtures :each tmp-db-clean)

(def json-header {"Content-Type" "application/json"})

(deftest create-valid-planet
  (testing "test create a valid planets."
    (let [response-A (make-request :post "/planets" factory/planet-A)
          response-B (make-request :post "/planets" factory/planet-B)
          response-C (make-request :post "/planets" factory/planet-C)
          response-D (make-request :post "/planets" factory/planet-D)
          response-E (make-request :post "/planets" factory/planet-E)
          response-F (make-request :post "/planets" factory/planet-F)
          response-G (make-request :post "/planets" factory/planet-G)
          response-H (make-request :post "/planets" factory/planet-H)
          response-I (make-request :post "/planets" factory/planet-I)
          response-J (make-request :post "/planets" factory/planet-J)]
      (is (= (:status response-A) 200))
      (is (= (:status response-B) 200))
      (is (= (:status response-C) 200))
      (is (= (:status response-D) 200))
      (is (= (:status response-E) 200))
      (is (= (:status response-F) 200))
      (is (= (:status response-G) 200))
      (is (= (:status response-I) 200))
      (is (= (:status response-J) 200)))))

(deftest create-duplicate-planet
  (testing "test create two planet with same name."
    (let [response-A (make-request :post "/planets" factory/planet-A)
          response-B (make-request :post "/planets" factory/planet-A)]
      (is (= (:status response-A) 200))
      (is (= (:status response-B) 409)))))

(deftest create-invalid-planet-params
  (testing "test create a planet with invalid params."
    (let [response-A (make-request :post "/planets" {"bar" "foo"})
          response-B (make-request :post "/planets" {"name" "Alderaan", "climate" "temperate", "terrain" 1})
          response-C (make-request :post "/planets" {"name" "Alderaan", "climate" true, "terrain" "glasslands, mountains"})
          response-D (make-request :post "/planets" {"name" 0, "climate" "temperate", "terrain" "glasslands, mountains"})
          response-E (make-request :post "/planets" {"_name" "Alderaan", "climate" "temperate", "terrain" "glasslands, mountains"})
          response-F (make-request :post "/planets" {})]
      (is (= (:status response-A) 400))
      (is (= (:status response-B) 400))
      (is (= (:status response-C) 400))
      (is (= (:status response-D) 400))
      (is (= (:status response-E) 400))
      (is (= (:status response-F) 400)))))

(deftest list-planets
  (testing "test list planets."
    (make-request :post "/planets" factory/planet-A)
    (let [response-A (make-request :get "/planets")]
;      (is (= (:status response-A) 200))
;      (is (= factory/planet-A (parse-string (:body response-A)))))))