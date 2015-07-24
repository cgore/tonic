(ns tonic.core-test
  (:require [clojure.test :refer :all]
            [tonic.test-utils :refer :all]
            [tonic.core :refer :all]))

(deftest integral-number-test
  (is   (valid? IntegralNumber 12))
  (is   (valid? IntegralNumber (long 12)))
  (is   (valid? IntegralNumber (bigint 12)))
  (is (invalid? IntegralNumber 12.34)))
