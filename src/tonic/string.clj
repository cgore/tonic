(ns tonic.string
  (:require [schema.core :as s :refer [defschema]]))

;;; You can use `String` as a schema already.

(defschema NonEmptyString
  "Matches any non-empty string, that is, excluding \"\"."
  (s/both String
          (s/pred #(not (empty? %)) :non-empty-string)))
