All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### Added
* Add SEPA Creditor Identifier support (#7).
* Add ISO country Kosovo (XK) (#14).
* Add ISO currency Bolívar Soberano (VES) (#33).
* Add Latvia to the list of country for ISO Currency Euro (EUR) (#33).
* Add support for Jordan (JO) IBAN numbers (#14).
* Add support for Qatar (QA) IBAN numbers (#14).
* Add support for Timor-Leste (TL) IBAN numbers (#14).
* Add support for Belarus (BY) IBAN numbers (#16).
* Add support for Virgin Islands (VG) IBAN numbers (#16).
* Add support for El Salvador (SV) IBAN numbers (#16).
* Add support for Mayotte (YT) IBAN numbers (as a sub-country of France (FR)) (#16).
* Add support for Iraq (IQ) IBAN numbers (#16).
* Add support for Kosovo (XK) IBAN numbers (#16).
* Add support for Saint Lucia (LC) IBAN numbers (#16).
* Add support for Sao Tome and Principe (ST) IBAN numbers (#16).
* Add support for Seychelles (SC) IBAN numbers (#16).
* Add support for Ukraine (UA) IBAN numbers (#16).
* Add support for Isle of Man (IM) IBAN numbers (as a sub-country of United Kingdom (UK)) (#16).
* Add support for Jersey (JE) IBAN numbers (as a sub-country of United Kingdom (UK)) (#16).
* Add support for Guernsey (GG) IBAN numbers (as a sub-country of United Kingdom (UK)) (#16).
* Add support for Egypt (EG) IBAN numbers (#30).
* Add support for Vatican City State (VA) IBAN numbers (#30).

### Changed
* Update BBAN structure for Costa Rica (CR) (#16).
* Update BBAN structure for Finland (FI) (#16).
* Update Belarusian Ruble ISO 4217 currency code from `BYR` to `BYN` (see
  https://wikipedia.org/wiki/Belarusian_ruble) (#33).
* Update Dobra ISO 4217 currency code from `STD` to `STN` (see
  https://wikipedia.org/wiki/S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe_dobra) (#33).
* Fix `BELARUSSIAN_RUBLE` currency name to `BELARUSIAN_RUBLE` (#33).
* Update Ouguiya ISO 4217 currency code from `MRO` to `MRU` (see
  https://wikipedia.org/wiki/Ouguiya) (#33).
* Fix BBAN structure for Turkey (TR) (#30).
* Drop support for Java 6 and Java 7 (#21).

## Deleted
* Remove Åland Islands (AX) BBAN structure and make it a sub-country of Finland (FI) (#16).
* Remove `IsoCurrency` entry `BOLIVAR` (VEF) (see https://wikipedia.org/wiki/Venezuelan_bol%C3%ADvar)
  (#33).
* Remove `IsoCurrency` entry `LATVIAN_LATS` (LVL) (see https://wikipedia.org/wiki/Latvian_lats) (#33).
* Remove `IsoCurrency` entry `UIC_FRANC` (XFU) (see https://wikipedia.org/wiki/UIC_franc) (#33).

Many thanks to @ckayser, @opeti and @ajsutton for their help !
