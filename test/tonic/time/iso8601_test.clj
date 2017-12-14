(ns tonic.time.iso8601-test
  (:require [clojure.test :refer :all]
            [tonic.test-utils :refer :all]
            [tonic.time.iso8601 :as iso8601]
            [schema.core :as s]))

(deftest year
  (testing "matches a year string"
    (is (valid? iso8601/Year "1978")))
  (testing "rejects non-year strings"
    (is (invalid? iso8601/Year "frobinating"))))

(deftest calendar-date
  (testing "matches a YYYY-MM-dd calendar date"
    (is (valid? iso8601/CalendarDate "1978-12-08")))
  (testing "rejects non-date strings"
    (is (invalid? iso8601/CalendarDate "frobinating")))
  (testing "rejects bare years"
    (is (invalid? iso8601/CalendarDate "1978"))))

(deftest time
  (testing "matches on a valid time"
    (is (valid? iso8601/Time "11:30"))
    (is (valid? iso8601/Time "11:22:33"))
    (is (valid? iso8601/Time "11:22:33.444")))
  (testing "rejects non-time strings"
    (is (invalid? iso8601/Time "frobinating"))))

(deftest date-time-z
  (testing "matches Z date time strings"
    (is (valid? iso8601/DateTimeZ "1978-12-08T11:22:33Z"))
    (is (valid? iso8601/DateTimeZ "1978-12-08T11:22:33.444Z")))
  (testing "fails on other timezones"
    (is (invalid? iso8601/DateTimeZ "1978-12-08T11:22:33-06:00")))
  (testing "rejects non-date-time-z strings"
    (is (invalid? iso8601/DateTimeZ "frobinating"))))
