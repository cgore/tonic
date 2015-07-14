(ns tonic.string-test
  (:refer-clojure :exclude [re-matches])
  (:require [clojure.test :refer :all]
            [tonic.string :refer :all]
            [schema.core :as s]))

(defn valid? [schema thing]
  (nil? (s/check schema thing)))

(defn invalid? [schema thing]
  (not (valid? schema thing)))

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
