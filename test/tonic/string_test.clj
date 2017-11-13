(ns tonic.string-test
  (:refer-clojure :exclude [re-matches])
  (:require [clojure.test :refer :all]
            [tonic.test-utils :refer :all]
            [tonic.string :refer :all]
            [schema.core :as s]))

(deftest empty-string-test
  (is (valid? EmptyString ""))
  (is (invalid? EmptyString "foo"))
  (is (invalid? EmptyString nil)))

(deftest non-empty-string-test
  (is (valid? NonEmptyString "foo"))
  (is (invalid? NonEmptyString ""))
  (is (invalid? NonEmptyString :foo)))

(deftest re-matches-test
  (is (valid? (re-matches #"[a-z]+") "foo"))
  (is (invalid? (re-matches #"[a-z]+") "foo123"))
  (is (invalid? (re-matches #"[a-z]+") :foo)))

(comment ; How to get UUIDs:
  (str (java.util.UUID/randomUUID)))

(deftest lowercase-uuid-regex-test
  (is (valid? LowercaseUUID "c9254dc7-6626-4f94-ab80-9cf43d6666c2"))
  (testing "no uppercase"
    (is (invalid? LowercaseUUID "c9254dc7-6626-4F94-ab80-9cf43d6666c2")))
  (testing "too few in first chunk"
    (is (invalid? LowercaseUUID "c9254dc-6626-4f94-ab80-9cf43d6666c2")))
  (testing "invalid in last chunk"
    (is (invalid? LowercaseUUID "c9254dc7-6626-4f94-ab80-9cf43d6666z2"))))

(deftest uppercase-uuid-regex-test
  (is (valid? UppercaseUUID "C9254DC7-6626-4F94-AB80-9CF43D6666C2"))
  (testing "no lowercase"
    (is (invalid? UppercaseUUID "C9254DC7-6626-4f94-AB80-9CF43D6666C2")))
  (testing "too few in first chunk"
    (is (invalid? UppercaseUUID "C9254DC-6626-4F94-AB80-9CF43D6666C2")))
  (testing "invalid in last chunk"
    (is (invalid? UppercaseUUID "C9254DC7-6626-4F94-AB80-9CF43D6666Z2"))))
