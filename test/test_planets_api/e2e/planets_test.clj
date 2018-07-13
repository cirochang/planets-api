(ns test-planets-api.e2e.planets-test
  (:require [clojure.test :refer :all]
            [test-planets-api.helpers.fixtures :refer [tmp-db-init tmp-db-clean]]
            [test-planets-api.helpers.utils :refer [make-request submap?]]
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

(deftest get-list-planets
  (testing "test get list of NONE planets."
    (let [response (make-request :get "/planets")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 0))))
  (testing "test get list of ONE planet."
    (make-request :post "/planets" factory/planet-A)
    (let [response (make-request :get "/planets")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 1))
      (is (some #(submap? factory/planet-A %) res-planets))))
  (testing "test get list of TWO planets." 
    (make-request :post "/planets" factory/planet-B)
    (let [response (make-request :get "/planets")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 2))
      (is (some #(submap? factory/planet-A %) res-planets))
      (is (some #(submap? factory/planet-B %) res-planets))))
  (testing "test get list of 10 planets." 
    (make-request :post "/planets" factory/planet-C)
    (make-request :post "/planets" factory/planet-D)
    (make-request :post "/planets" factory/planet-E)
    (make-request :post "/planets" factory/planet-F)
    (make-request :post "/planets" factory/planet-G)
    (make-request :post "/planets" factory/planet-H)
    (make-request :post "/planets" factory/planet-I)
    (make-request :post "/planets" factory/planet-J)
    (let [response (make-request :get "/planets")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 10))
      (is (some #(submap? factory/planet-A %) res-planets))
      (is (some #(submap? factory/planet-B %) res-planets))
      (is (some #(submap? factory/planet-C %) res-planets))
      (is (some #(submap? factory/planet-D %) res-planets))
      (is (some #(submap? factory/planet-E %) res-planets))
      (is (some #(submap? factory/planet-F %) res-planets))
      (is (some #(submap? factory/planet-G %) res-planets))
      (is (some #(submap? factory/planet-H %) res-planets))
      (is (some #(submap? factory/planet-I %) res-planets))
      (is (some #(submap? factory/planet-J %) res-planets)))))

(deftest get-planets-by-id
  (make-request :post "/planets" factory/planet-A)
  (make-request :post "/planets" factory/planet-B)
  (make-request :post "/planets" factory/planet-C)
  (make-request :post "/planets" factory/planet-D)
  (testing "test get planet by wrong id."
    (let [response (make-request :get "/planets/foobar")]
      (is (= (:status response) 404)))
    (let [response (make-request :get "/planets/123456")]
      (is (= (:status response) 404))))  
  (testing "test get planet by valid id."
    (let [response (make-request :get "/planets")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")
          res-planet-A (first (filter #(submap? factory/planet-A %) res-planets))
          response-B (make-request :get (str "/planets/" (get res-planet-A "_id")))
          res-body-B (parse-string (:body response-B))]
      (is (= (:status response-B) 200))
      (is (= res-planet-A res-body-B)))))

(deftest get-planets-by-name
  (make-request :post "/planets" factory/planet-A)
  (make-request :post "/planets" factory/planet-B)
  (make-request :post "/planets" factory/planet-C)
  (make-request :post "/planets" factory/planet-D)
  (testing "test get planet with wrong names."
    (let [response (make-request :get "/planets?name=foobar")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 0)))
    (let [response (make-request :get "/planets?name=123456")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 0))))
  (testing "test get planet by valid name."
    (let [response (make-request :get (str "/planets?name=" (get factory/planet-A "name")))
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 1))
      (is (some #(submap? factory/planet-A %) res-planets)))
    (let [response (make-request :get (str "/planets?name=" (get factory/planet-C "name")))
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 1))
      (is (some #(submap? factory/planet-C %) res-planets)))))

(deftest remove-planet-by-id
  (make-request :post "/planets" factory/planet-A)
  (make-request :post "/planets" factory/planet-B)
  (make-request :post "/planets" factory/planet-C)
  (make-request :post "/planets" factory/planet-D)
  (testing "test remove planet with wrong ids."
    (let [response (make-request :delete "/planets/foobar")]
      (is (= (:status response) 404)))
    (let [response (make-request :delete "/planets/123456")]
      (is (= (:status response) 404))))  
  (testing "test remove planet by valid id."
    (let [response (make-request :get "/planets")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")
          res-planet-A (first (filter #(submap? factory/planet-A %) res-planets))
          response-B (make-request :delete (str "/planets/" (get res-planet-A "_id")))]
      (is (= (:status response-B) 200)))
    (let [response (make-request :get "/planets")
          res-body (parse-string (:body response))
          res-planets (get res-body "planets")]
      (is (= (:status response) 200))
      (is (= (count res-planets) 3))
      (is (not (some #(submap? factory/planet-A %) res-planets))))))