All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This new release fixes issues with IBAN and creditor identifier check digit validation.

### Added

### Changed

### Fixed

- Fix IBAN and creditor identifier check digit validation (#82). Validation of IBANs and creditor identifiers with a check digit equals to `00`, `01` or `99`
  will now raise an `IllegalArgumentException` instead of just being considered invalid. Note that this fix has an impact on both IBANs and creditor identifiers
  validation because they both use the same _ISO 7064 Mod 97,10_ check digit validation.

### Deprecated

### Removed

## Internal

### Thanks

Many thanks to @edgeofoblivion for his help !
