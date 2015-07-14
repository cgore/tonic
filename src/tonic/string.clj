(ns tonic.string
  (:refer-clojure :exclude [re-matches])
  (:require [schema.core :as s :refer [defschema]]))

;;; You can use `String` as a schema already.

(defschema EmptyString
  "Matches the empty string."
  (s/both String
          (s/pred empty? :empty-string)))

(defschema NonEmptyString
  "Matches any non-empty string, that is, excluding \"\"."
  (s/both String
          (s/pred #(not (empty? %)) :non-empty-string)))

(defn re-matches
  "Matches any string that matches the regular expression provided."
  ([re]
   (re-matches re ""))
  ([re re-name]
   (s/both String
           (s/pred #(clojure.core/re-matches re %) re-name))))
