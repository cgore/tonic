(ns tonic.internet
  (:require [schema.core :as s :refer [defschema]]
            [tonic.string :as t-str]))

(defschema IPv4Address
  "Matches IPv4 addresses represented in their typical dotted notation."
  (s/both String
          (t-str/re-matches #"\d+\.\d+\.\d+\.\d+" :ipv4-address)
          (s/pred (fn [address]
                    (every? #(<= 0 % 255)
                            (map #(Integer. %)
                                 (clojure.string/split address #"\."))))
                  :ipv4-address)))

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
