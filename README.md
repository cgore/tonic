# tonic

All of the schemata!

Tonic goes well with a healthy dose of [https://github.com/cgore/gin](gin).

[![Clojars Project](http://clojars.org/tonic/latest-version.svg)](http://clojars.org/tonic)

[![Build Status](https://travis-ci.org/cgore/tonic.svg?branch=master)](https://travis-ci.org/cgore/tonic)

## Usage

### `tonic.char`

You can validate lower case letters.

```clojure
(s/check AlphaLower \q)
;; => nil
(s/check AlphaLower \Q)
;; => (not (:lower-case-alpha-character \Q))
```

You can validate upper case letters.

```clojure
(s/check AlphaUpper \Q)
;; => nil
(s/check AlphaUpper \q)
;; => (not (:upper-case-alpha-character \q))
```

You can validate letters.

```clojure
(s/check Alpha \q)
;; => nil
(s/check Alpha \Q)
;; => nil
(s/check Alpha :a)
;; => (named (not (some (check % :a) schemas)) :alpha-character)
```

You can validate numeric digits.

```clojure
(s/check Digit \4)
;; => nil
(s/check Digit \d)
;; => (named (not (#{\0 \1 \2 \3 \4 \5 \6 \7 \8 \9} \d)) :numeric-digit)
```

You can validate alpha-numeric characters.

```clojure
(s/check AlphaNumeric \4)
;; => nil
(s/check AlphaNumeric \q)
;; => nil
(s/check AlphaNumeric \Q)
;; => nil
(s/check AlphaNumeric 7)
;; => (named (not (some (check % 7) schemas)) :alpha-numeric-character)
```

You can validate lower case hex digits.

```clojure
(s/check HexLower \5)
;; => nil
(s/check HexLower \f)
;; => nil
(s/check HexLower \F)
;; => (named (not (some (check % \F) schemas)) :hex-lower-case-digit)
```

You can validate upper case hex digits.

```clojure
(s/check HexUpper \5)
;; => nil
(s/check HexUpper \F)
;; => nil
(s/check HexUpper \f)
;; => (named (not (some (check % \f) schemas)) :hex-upper-case-digit)
```

You can validate hex digits irrespective of upper/lower case.

```clojure
(s/check Hex \5)
;; => nil
(s/check Hex \f)
;; => nil
(s/check Hex \F)
;; => nil
(s/check Hex \g)
;; => (named (not (some (check % \g) schemas)) :hex-digit)
```

### `tonic.core`

## License

Copyright © 2015 Christopher Mark Gore, Soli Deo Gloria, all rights reserved.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
