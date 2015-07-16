(ns tonic.char-test
  (:require [clojure.test :refer :all]
            [tonic.test-utils :refer :all]
            [tonic.char :refer :all]
            [schema.core :as s]))

(deftest alpha-lower-test
  (is (valid? AlphaLower \a))
  (is (valid? AlphaLower \q))
  (is (valid? AlphaLower \z))
  (is (invalid? AlphaLower \A)))

(deftest alpha-upper-test
  (is (valid? AlphaUpper \A))
  (is (valid? AlphaUpper \Q))
  (is (valid? AlphaUpper \Z))
  (is (invalid? AlphaUpper \a)))

(deftest alpha-test
  (is (valid? Alpha \a))
  (is (valid? Alpha \A))
  (is (valid? Alpha \z))
  (is (valid? Alpha \Z))
  (is (invalid? Alpha \4)))

(deftest digit-test
  (is (valid? Digit \0))
  (is (valid? Digit \9))
  (is (invalid? Digit \a)))

(deftest alpha-numeric-test
  (is (valid? AlphaNumeric \a))
  (is (valid? AlphaNumeric \q))
  (is (valid? AlphaNumeric \z))
  (is (valid? AlphaNumeric \A))
  (is (valid? AlphaNumeric \Q))
  (is (valid? AlphaNumeric \Z))
  (is (valid? AlphaNumeric \0))
  (is (valid? AlphaNumeric \9))
  (is (invalid? AlphaNumeric \!)))

(deftest hex-lower-test
  (is (valid? HexLower \0))
  (is (valid? HexLower \9))
  (is (valid? HexLower \a))
  (is (valid? HexLower \f))
  (is (invalid? HexLower \F))
  (is (invalid? HexLower \g)))

(deftest hex-upper-test
  (is (valid? HexUpper \0))
  (is (valid? HexUpper \9))
  (is (valid? HexUpper \A))
  (is (valid? HexUpper \F))
  (is (invalid? HexUpper \f))
  (is (invalid? HexUpper \G)))

(deftest hex-test
  (is (valid? Hex \0))
  (is (valid? Hex \9))
  (is (valid? Hex \a))
  (is (valid? Hex \f))
  (is (valid? Hex \A))
  (is (valid? Hex \F))
  (is (invalid? Hex \g)))
