All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This new release includes :
- a review of the ISO 3166 countries, ISO 4217 currencies and BBAN structure enums,
- `Calendar` API improvements (new methods, composite calendars),
- a few fixes for `IsoCurrency`,
- serialization support.

This new release also includes breaking changes (see details below).

### Added

- Add support for composite calendars, e.g. calendars calendars that combine multiple calendars into
  a single one.
- Add `Calendar.previousOrSame(LocalDate date)` : compute the previous business day before the given
  date (included) (#61).
- Add `Calendar.nextOrSame(LocalDate date)` : compute the next business day before the given date
  (included) (#61).
- Add `Calendar.shift(LocalDate date, int amount)` : shifts the date by the specified number of
  business days (#61).
- Add support for Libyan (LY) IBAN numbers (#63).

### Changed

- Make jbanking serializable-friendly (#53).
- (breaking change) `IsoCountry` and `IsoCurrency` enums entries renamed using their alpha-code
  instead of their full names to reduce breaking changes in future versions (#56).
- (breaking change) Changed `IsoCurrency.fromNumericCode(Integer)` signature to
  `IsoCurrency.fromNumericCode(int)` (#56).
- (breaking change) Rename `Calendar.previousBusinessDay` to `Calendar.previous` (#61).
- (breaking change) Rename `Calendar.nextBusinessDay` to `Calendar.next` (#61).
- (breaking change) Change return type of `IsoCurrency.fromAlphabeticCode(String code)` to
  `Optional<IsoCurrency>` (#62).
- (breaking change) Change return type of `IsoCurrency.fromNumericCode(int code)` to
  `Optional<IsoCurrency>` (#62).
- (breaking change) Change return type of `BbanStructure.forCountry(IsoCountry country)` to
  `Optional<BbanStructure>` (#62).

### Fixed

- (breaking change) Remove Lithuanian litas (LTL) entry from `IsoCurrency` enum : Lithuania is using
  Euro since 2015 (#59).
- Fix `IsoCurrency.CDF` ([Congolese franc](https://en.wikipedia.org/wiki/Congolese_franc)) enum
  entry : the entry declared [the Congo (CG)](https://www.iso.org/obp/ui/#iso:code:3166:CG) instead
  of [the Democratic Republic of the Congo (CD)](https://www.iso.org/obp/ui/#iso:code:3166:CD) (#60).
- Fix Unidad de Fomento (`IsoCurrency.CLF`) minor unit (#63).

### Deprecated

### Removed

- (breaking change) Remove deprecated method `IsoCountry.getCode()` : use
  `IsoCountry.getAlpha2Code()` instead (#49).
- (breaking change) Remove deprecated method `IsoCountry.fromCode(String)` : use
  `IsoCountry.fromAlpha2Code(String)` instead (#49).
- (breaking change) Remove `IsoCurrency.NO_UNIVERSAL_CURRENCY` : this currency has no associated code
  and could not be retained during `IsoCurrency` enum entries renaming (#56).

## Internal

- Upgrade to Strata 2.8.2 (#57).
- Upgrade to Guava 30.0 (#58).
- Upgrade to parent 2.3.2 (#65).
- Make use of lookup tables in `IsoCountry`, `IsoCurrency` and `BbanStructure` (#62).

### Thanks
