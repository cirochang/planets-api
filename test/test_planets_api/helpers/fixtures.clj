(ns test-planets-api.helpers.fixtures
    (:require [monger.core :as mg]
              [planets-api.utils.database :refer [db create-db destroy-db clean-db]]))

(defn tmp-db-init
  "Use a temporary database for the tests."
  [f]
  (with-redefs [planets-api.utils.database/db (mg/get-db (mg/connect) "test-planets-api")]
    (create-db)
    (f)
    (destroy-db)))

(defn tmp-db-clean
  "User to clean the temporary database"
  [f]
  (with-redefs [planets-api.utils.database/db (mg/get-db (mg/connect) "test-planets-api")]
    (clean-db)
    (f)))