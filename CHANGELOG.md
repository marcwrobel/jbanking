All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This new release includes :
- serialization support,
- Calendar API improvements (new methods, composite calendars),
- a few fixes for `IsoCurrency`.

This new release also includes breaking changes (see details below) :
- renaming of `IsoCountry` and `IsoCurrency` enums entries,
- removal of `IsoCountry.NO_UNIVERSAL_CURRENCY`,
- removal of deprecated methods.
- renaming in `Calendar` interface,

### Added

- Add support for composite calendars, e.g. calendars calendars that combine multiple calendars into
  a single one.
- Add `Calendar.previousOrSame(LocalDate date)` : compute the previous business day before the given
  date (included) (#61).
- Add `Calendar.nextOrSame(LocalDate date)` : compute the next business day before the given date
  (included) (#61).
- Add `Calendar.shift(LocalDate date, int amount)` : shifts the date by the specified number of
  business days (#61).

### Changed

- Make jbanking serializable-friendly (#53).
- Upgrade to Strata 2.8.2 (#57).
- Upgrade to Guava 30.0 (#58).
- `IsoCountry` and `IsoCurrency` enums entries renamed using their alpha-code instead of their full
  names to reduce breaking changes in future versions (#56).
- Changed `IsoCurrency.fromNumericCode(Integer)` signature to `IsoCurrency.fromNumericCode(int)`
  (#56).
- Rename `Calendar.previousBusinessDay` to `Calendar.previous` (#61).
- Rename `Calendar.nextBusinessDay` to `Calendar.next` (#61).

### Fixed

- Remove Lithuanian litas (LTL) entry from `IsoCurrency` enum : Lithuania is using Euro since 2015
  (#59).
- Fix `IsoCountry.CDF` ([Congolese franc](https://en.wikipedia.org/wiki/Congolese_franc)) enum
  entry : the entry declared [the Congo (CG)](https://www.iso.org/obp/ui/#iso:code:3166:CG) instead
  of [the Democratic Republic of the Congo (CD)](https://www.iso.org/obp/ui/#iso:code:3166:CD) (#60).

### Deprecated

### Removed

- Remove deprecated method `IsoCountry.getCode()` : use `IsoCountry.getAlpha2Code()` instead (#49).
- Remove deprecated method `IsoCountry.fromCode(String)` : use `IsoCountry.fromAlpha2Code(String)`
  instead (#49).
- Remove `IsoCountry.NO_UNIVERSAL_CURRENCY` : this currency has no associated code and could not be
  retained during `IsoCountry` enum entries renaming (#56).

### Thanks
