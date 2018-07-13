(defproject planets-api "1.0.0"
  :description "An exercise of planets API provided by B2W."
  :url "https://gitlab.com/cirochang/planets-api/"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.3.4"]
                 [ring/ring-json "0.1.2"]
                 [com.novemberain/monger "3.1.0"]
                 [clj-http "3.9.0"]
                 [prismatic/schema "1.1.7"]
                 [cheshire "5.6.1"]
                 [org.slf4j/slf4j-nop "1.7.12"]]
  :plugins [[lein-ring "0.12.3"]]
  :ring {:handler planets-api.main/app}
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[ring/ring-mock "0.3.2"]
                                  [ring/ring-core "1.6.3"]
                                  [javax.servlet/servlet-api "2.5"]]}})