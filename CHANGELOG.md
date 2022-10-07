All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### Added

- Document alternatives to jbanking (#164).
- Make `CreditorIdentifier#REGEX` public (as part of #172).

### Changed

- (**breaking change**) Make `CreditorIdentifier` final (#116).
- (**breaking change**) Rename `Bic#BIC_REGEX` to `Bic#REGEX` and change it to not accept lower-case characters anymore
  (as part of #170).
- Get rid of regexes to validate BICs (#170). This significantly increased the performances of BIC validation (+200%)
  and creation (+300%).
- Get rid of regexes to validate Creditor Identifiers (#172). This significantly increased the performances of
  Creditor Identifiers validation (+30-40%) and creation (+30-50%).
- Improve javadoc (as part of #170 and #172).

### Fixed

- (**breaking change**) `Iban.isValid(String)` or `IbanCheckDigit.validate(String)` return `false` instead of raising an
  `IllegalArgumentException` with invalid IBAN check digit (e.g. `00`, `01`, or `99`).

### Deprecated

### Removed

- (**breaking change**) Remove `Bic#BIC_PATTERN` (as part of #170). If you still need to use the BIC regex, you may compile
  the pattern from `Bic#REGEX`, which has been kept for compatibility and documentation purposes.

### Internal

- Bump strata-basics from 2.12.5 to 2.12.7 (#179).
- Bump github/codeql-action from 2.1.16 to 2.1.22 (#192).
- Bump actions/setup-java from 3.4.1 to 3.5.1 (#195, #202).
- Bump actions/checkout from 3.0.2 to 3.1.0 (#205).
- Alphabetically sort `IsoCurrency`, `Holidays` and `BbanStructure` enums entries (#161).
- Add benchmarks that track the evolution of jbanking performances from version to version (#166). Some numbers can be
  seen [here](benchmarks/README.md).
- Increase JVM memory for Maven (#184). Sonar analysis fails with `OutOfMemoryError`.
- Remove bin/check-links (#129). The script has been moved to https://github.com/marcwrobel/checklinks.
- Configure dependabot to check for update of GitHub actions versions (#189).
- Add OpenSSF scorecard badge to the README (#193).

### Thanks
