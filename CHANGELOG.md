All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This new release includes :
- an alignment of the IBAN definitions to [IBAN Registry Release 87](https://www.swift.com/standards/data-standards/iban) (May 2020).

### Added
- Set jbanking `Automatic-Module-Name` to `fr.marcwrobel.jbanking` (#42).
- Countries' participation to _European Union_ (#44).
- Countries' participation to _Single Euro Payments Area_, also known as SEPA (#31).
- Countries' participation to _SEPA COM Pacifique_ (#46).

### Changed
- Adjust BBAN structure for Sao Tome and Principe (#36).
- Upgrade to parent [2.3.0](https://github.com/marcwrobel/parent/releases/tag/v2.3.0) (#40).
- Upgrade to guava [29.0](https://github.com/google/guava/releases/tag/v29.0) (#41).
- Add ISO 3166-1 alpha-3 and numeric codes to IsoCountry (#43).

### Fixed
- Sonar analysis fails on merge requests (#39).

Many thanks to @kayman-mk for his help !
