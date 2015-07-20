# tonic

All of the schemata!

Tonic is a library of generally useful schemata using [Prismatic Schema](https://github.com/Prismatic/schema).

Tonic goes well with a healthy dose of [gin](https://github.com/cgore/gin).

[![Clojars Project](http://clojars.org/tonic/latest-version.svg)](http://clojars.org/tonic)

[![Build Status](https://travis-ci.org/cgore/tonic.svg?branch=master)](https://travis-ci.org/cgore/tonic)

[![Dependencies Status](http://jarkeeper.com/cgore/tonic/status.png)](http://jarkeeper.com/cgore/tonic)

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

### `tonic.internet`

You can validate IP addresses (IPv4 specifically.)

```clojure
(s/check IPv4Address "192.168.1.1")
;; => nil
(s/check IPv4Address "192.168.1.256")
;; => (not (:ipv4-address "192.168.1.256"))
```

You can validate MAC addresses. It's important to realize that there are
actually three common formats for displaying MAC addresses, although the most
common by far is the colon-separated variant.

```clojure
(s/check MacAddressColons "a1:b2:c3:d4:e5:f6")
;; => nil
(s/check MacAddressHyphens "a1-b2-c3-d4-e5-f6")
;; => nil
(s/check MacAddressPeriods "a1b2.c3d4.e5f6")
;; => nil
(s/check MacAddress "a1:b2:c3:d4:e5:f6")
;; => nil
(s/check MacAddress "a1-b2-c3-d4-e5-f6")
;; => nil
(s/check MacAddress "a1b2.c3d4.e5f6")
;; => nil
(s/check MacAddress "a1:b2:c3:d4:e5")
;; => (named (not (some (check % "a1:b2:c3:d4:e5") schemas)) :mac-address)
(s/check MacAddress "a1:b2:c3:d4:e5:f6:f6")
;; => (named (not (some (check % a-java.lang.String) schemas)) :mac-address)
```

### `tonic.string`

You can validate for empty or non-empty strings.

```clojure
(s/check EmptyString "")
;; => nil
(s/check EmptyString "foo")
;; => (not (:empty-string "foo"))
(s/check NonEmptyString "foo")
;; => nil
(s/check NonEmptyString "")
;; => (not (:non-empty-string ""))
(s/check NonEmptyString :foo)
;; => (not (instance? java.lang.String :foo))
```

You can validate for a string matching a specific regular expression.
You can also provide an optional name for the matched regular expression.

```clojure
(s/check (re-matches #"[a-z]+") "foo")
;; => nil
(s/check (re-matches #"[a-z]+" :lowercase) "foo123")
;; => (not (:lowercase "foo123"))
```

## License

Copyright © 2015 Christopher Mark Gore, Soli Deo Gloria, all rights reserved.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
