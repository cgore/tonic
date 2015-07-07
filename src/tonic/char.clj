(ns tonic.char
  (:require [schema.core :as s :refer [defschema]]))

;;; You can use `Character` as a schema already.

(defschema AlphaLower
  "Matches lowercase alpha characters."
  (s/both Character
          (s/pred #(<= (int \a) (int %) (int \z)) :lower-case-alpha-character)))

(defschema AlphaUpper
  "Matches uppercase alpha characters."
  (s/both Character
          (s/pred #(<= (int \A) (int %) (int \Z)) :upper-case-alpha-character)))

(defschema Alpha
  "Matches alpha characters."
  (s/named (s/either AlphaLower AlphaUpper) :alpha-character))

(defschema Digit
  "Matches numeric digits."
  (s/named (s/enum \0 \1 \2 \3 \4 \5 \6 \7 \8 \9) :numeric-digit))

(defschema AlphaNumeric
  "Matches alphanumeric characters."
  (s/named (s/either Alpha Digit) :alpha-numeric-character))

(defschema HexLower
  "Matches hex number characters, only allowing lowercase letters for a-f."
  (s/named (s/either Digit (s/enum \a \b \c \d \e \f)) :hex-lower-case-digit))

(defschema HexUpper
  "Matches hex number characters, only allowing uppercase letters for a-f."
  (s/named (s/either Digit (s/enum \A \B \C \D \E \F)) :hex-upper-case-digit))

(defschema Hex
  "Matches hex number characters."
  (s/named (s/either HexLower HexUpper) :hex-digit))
