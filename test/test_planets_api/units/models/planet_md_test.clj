(ns test-planets-api.units.models.planet-md-test
  (:require [clojure.test :refer :all]
            [planets-api.models.planet-md :as planet-md]
            [test-planets-api.helpers.factories :as factory]))

(deftest test-get-total-films-by-planet
  (testing "test function get-total-films-by-planet"
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-A) 2))
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-B) 1))
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-C) 1))
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-D) 3))
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-E) 1))
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-F) 1))
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-G) 4))
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-I) 1))
    (is (= (#'planet-md/get-total-films-by-planet factory/planet-J) 1))))
