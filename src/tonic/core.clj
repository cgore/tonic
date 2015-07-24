(ns tonic.core
  (:require [schema.core :as s :refer [defschema]]))

;;; You can use `Double` as a schema already.

;;; You can use `Float` as a schema already.

(defschema IntegralNumber
  "Matches any integral numbers."
  (s/named (s/either Integer Long java.math.BigInteger clojure.lang.BigInt)
           :integral-number))
