All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This new release includes :
- serialization support,
- calendar API improvements (composite calendars),

This new release also includes breaking changes :
- deprecated methods removal (see details below).

### Added

- Add support for composite calendars, e.g. calendars calendars that combine multiple calendars into
  a single one.

### Changed

- Make jbanking serializable-friendly (#53).
- Upgrade to Strata 2.8.2 (#57).
- Upgrade to Guava 30.0 (#58).

### Fixed

### Deprecated

### Removed

- Remove deprecated method `IsoCountry.getCode()` : use `IsoCountry.getAlpha2Code()` instead (#49).
- Remove deprecated method `IsoCountry.fromCode(String)` : use `IsoCountry.fromAlpha2Code(String)`
  instead (#49).

### Thanks
