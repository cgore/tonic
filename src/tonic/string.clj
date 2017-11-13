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
   (s/both String
           (s/pred #(clojure.core/re-matches re %))))
  ([re re-name]
   (s/both String
           (s/pred #(clojure.core/re-matches re %) re-name))))

(def lowercase-uuid-regex
  #"[a-f\d]{8}(-[a-f\d]{4}){3}-[a-f\d]{12}")

(def uppercase-uuid-regex
  #"[A-F\d]{8}(-[A-F\d]{4}){3}-[A-F\d]{12}")

(defschema LowercaseUUID
  "This is a UUID in lowercase string representation."
  (re-matches lowercase-uuid-regex :lowercase-uuid))

(defschema UppercaseUUID
  "This is a UUID in uppercase string representation."
  (re-matches uppercase-uuid-regex :uppercase-uuid))

(defschema StringUUID
  "This is a UUID in either uppercase or lowercase representation.  Mixed case
  is disallowed and is probably a bad idea if you ever run into it."
  (s/either LowercaseUUID UppercaseUUID))
