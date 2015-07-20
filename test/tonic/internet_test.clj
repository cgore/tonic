(ns tonic.internet-test
  (:require [clojure.test :refer :all]
            [tonic.test-utils :refer :all]
            [tonic.internet :refer :all]
            [schema.core :as s]))

(deftest mac-address-colons-test
  (is   (valid? MacAddressColons "a1:b2:c3:d4:e5:f6"))
  (is (invalid? MacAddressColons "a1:b2:c3:d4:e5:f6:f6"))
  (is (invalid? MacAddressColons "a1:b2:c3:d4:e5:"))
  (is (invalid? MacAddressColons "a1:b2:c3:d4:e5"))
  (is (invalid? MacAddressColons "a1-b2-c3-d4-e5-f6"))
  (is (invalid? MacAddressColons "a1b2.c3d4.e5f6")))

(deftest mac-address-hyphens-test
  (is (invalid? MacAddressHyphens "a1:b2:c3:d4:e5:f6"))
  (is   (valid? MacAddressHyphens "a1-b2-c3-d4-e5-f6"))
  (is (invalid? MacAddressHyphens "a1-b2-c3-d4-e5-f6-f6"))
  (is (invalid? MacAddressHyphens "a1-b2-c3-d4-e5-"))
  (is (invalid? MacAddressHyphens "a1-b2-c3-d4-e5"))
  (is (invalid? MacAddressHyphens "a1b2.c3d4.e5f6")))

(deftest mac-address-periods-test
  (is (invalid? MacAddressPeriods "a1:b2:c3:d4:e5:f6"))
  (is (invalid? MacAddressPeriods "a1-b2-c3-d4-e5-f6"))
  (is   (valid? MacAddressPeriods "a1b2.c3d4.e5f6"))
  (is (invalid? MacAddressPeriods "a1b2.c3d4.e5f6.e5f6"))
  (is (invalid? MacAddressPeriods "a1b2.c3d4."))
  (is (invalid? MacAddressPeriods "a1b2.c3d4")))

(deftest mac-address-test
  (is   (valid? MacAddress "a1:b2:c3:d4:e5:f6"))
  (is   (valid? MacAddress "a1-b2-c3-d4-e5-f6"))
  (is   (valid? MacAddress "a1b2.c3d4.e5f6"))
  (is (invalid? MacAddress "nope"))
  (is (invalid? MacAddress 0x1234)))

(deftest ipv4-address-test
  (is   (valid? IPv4Address "0.0.0.0"))
  (is   (valid? IPv4Address "255.255.255.255"))
  (is (invalid? IPv4Address "0.0.0.0.0"))
  (is (invalid? IPv4Address "0.0.0"))
  (is (invalid? IPv4Address "nope"))
  (is (invalid? IPv4Address 0xdeadbeef)))
