 (ns tonic.test-utils
  (:require [clojure.test :refer :all]
            [schema.core :as s]))

(defn valid? [schema thing]
  (nil? (s/check schema thing)))

(defn invalid? [schema thing]
  (not (valid? schema thing)))

