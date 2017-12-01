(ns tonic.time.iso8601
  (:require [schema.core :as s :refer [defschema]]
            [tonic.string :as tstr]))

;; ISO-8601 allows for a lot of weird stuff.
;; I mostly just want stuff like:
;;   2017-11-06T12:33:45Z
;; I welcome additions.

;; https://en.wikipedia.org/wiki/ISO_8601

(def year-regex
  #"[0-9]{4}")

(defschema Year
  (tstr/re-matches year-regex :iso8601-year))

(def calendar-date-regex
  #"[0-9]{4}-[0-9]{2}-[0-9]{2}")

(defschema CalendarDate
  (tstr/re-matches year-regex :iso8601-calendar-date))

(def time-regex
  #"[0-9]{2}:[0-9]{2}(:[0-9]{2}(\.[0-9]+)?)?")

(defschema Time
  (tstr/re-matches time-regex :iso8601-time))

(def date-time-z-regex
  (re-pattern (str calendar-date-regex "T" time-regex "Z")))

(defschema DateTimeZ
  (tstr/re-matches date-time-z-regex :iso8601-date-time-z))

(def DragonBallZ
  DateTimeZ)
