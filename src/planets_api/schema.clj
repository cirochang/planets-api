
(ns planets-api.schema
    (:require [schema.core :refer [required-key Str]]))
  
(def planet
    "A schema for planet creation."
    {(required-key "name") Str
     (required-key "climate") Str
     (required-key "terrain") Str})