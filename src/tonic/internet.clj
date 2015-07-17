(ns tonic.internet
  (:require [schema.core :as s :refer [defschema]]
            [tonic.string :as t-str]))

(defschema MacAddressColons
  "Matches MAC addresses in colon-separated hex notation."
  (t-str/re-matches #"([0-9a-f]{2}:){5}[0-9a-f]{2}" :mac-address-colons))

(defschema MacAddressHyphens
  "Matches MAC addresses in hyphen-separated hex notation."
  (t-str/re-matches #"([0-9a-f]{2}-){5}[0-9a-f]{2}" :mac-address-hyphens))

(defschema MacAddressPeriods
  "Matches MAC addresses in hyphen-separated hex notation."
  (t-str/re-matches #"([0-9a-f]{4}\.){2}[0-9a-f]{4}" :mac-address-periods))

(defschema MacAddress
  "Matches MAC addresses in any valid string representation format."
  (s/named (s/either MacAddressColons MacAddressHyphens MacAddressPeriods)
           :mac-address))
