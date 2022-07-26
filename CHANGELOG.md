All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### Added

- Document alternatives to jbanking (#164).

### Changed

- (**breaking change**) Make `CreditorIdentifier` final (#116).
- (**breaking change**) Rename `Bic#BIC_REGEX` to `Bic#REGEX` (as part of #170).
- Get rid of regexes to validate BICs (#170). This significantly increased the performances of BIC validation (x3) and
  creation (x4).

### Fixed

### Deprecated

### Removed

- (**breaking change**) Remove `Bic#BIC_PATTERN` (as part of #170). If you still need to use the BIC regex, you may compile
  the pattern from `Bic#REGEX`, which has been kept for compatibility and documentation purposes.

### Internal

- Alphabetically sort `IsoCurrency`, `Holidays` and `BbanStructure` enums entries (#161).
- Bump strata-basics from 2.12.5 to 2.12.6 (#165).
- Add benchmarks that track the evolution of jbanking performances from version to version (#166).

### Thanks
