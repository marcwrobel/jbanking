All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### Added

- Document alternatives to jbanking (#164).
- Make `CreditorIdentifier#REGEX` public (as part of #172).
- Make `Iban#REGEX` public (as part of #171).
- Add `SwiftPatternCharacterRepresentation` to centralize information about SWIFT pattern character representations (as
  part of #171).
- Add random IBAN generation for all the ISO 13616-compliant national IBAN formats (#153).
- Make `BbanStructure` public (as part of #153). `BbanStructure` is an enum that holds countries BBAN structure as
  listed in the [IBAN registry](https://www.iso13616.org).

### Changed

- (**breaking change**) Make `CreditorIdentifier` final (#116).
- (**breaking change**) Rename `Bic#BIC_REGEX` to `Bic#REGEX` and change it to not accept lower-case characters anymore
  (as part of #170).
- (**breaking change**) Make `Iban` not accepting values containing spaces anymore (as part of #116). This means
  printable or untrimmed IBANs are not considered valid values anymore. Note that this feature was not documented.
- (**breaking change**) Make `IbanCheckDigit#validate` return `false` for `null` or less than 5 characters strings
  (#188).
- (**breaking change**) Make `SwiftPattern#toString` returning only the SWIFT pattern expression (as part of #222).
  Prior to this version `SwiftPattern#toString` were returning the SWIFT pattern expression with its corresponding
  regular expression.
- (**breaking change**) Make `BicFormatException`, `CreditorIdentifierFormatException`, `IbanFormatException` and
  `SwiftPatternSyntaxException` extend `IllegalArgumentException` instead of `RuntimeException` (#220).
- Get rid of regexes to validate BICs (#170). This significantly increased the performances of BIC validation and
  creation (+200-300%).
- Get rid of regexes to validate Creditor Identifiers (#172). This significantly increased the performances of
  Creditor Identifiers validation and creation (+30-50%).
- Get rid of regexes to validate IBANs (#171). This significantly increased the performances of IBAN validation and
  creation (+250-400%).
- Improve javadoc (as part of #170 and #172).
- Add the _state funeral of Queen Elizabeth II_ day to London calendar (#204).
- Add the _national day of mourning for Her Majesty The Queen_ day to Sydney calendar (#204).
- Improve documentation of `Bic`, `CreditorIdentifier`, `Iban` and `SwiftPattern` regarding serialization (as part of
  #222).
- Update documentation to reflect the change from _Turkey_ to _Türkiye_ documented in the [ISO 4217 Currency Code
  Service - Amendment number 173](https://www.six-group.com/en/products-services/financial-information/data-standards.html)
  (#207).

### Fixed

- (**breaking change**) `Iban.isValid(String)` or `IbanCheckDigit.validate(String)` return `false` instead of raising an
  `IllegalArgumentException` with invalid IBAN check digit (e.g. `00`, `01`, or `99`).
- Fix `Bic`, `CreditorIdentifier` and `Iban` documentation (#209). It was not explicit that lowercase characters were
  accepted.

### Deprecated

### Removed

- (**breaking change**) Remove `Bic#BIC_PATTERN` (as part of #170). If you still need to use the BIC regex, you may
  compile the pattern from `Bic#REGEX`, which has been kept for compatibility and documentation purposes.
- (**breaking change**) Remove `BbanStructure#getBbanPattern` (as part of #171). `BbanStructure` does not use the
  `SwiftPattern` class anymore to validate BBAN. If you were using this method, use `BbanStructure#isBbanValid` instead.

### Internal

- Bump strata-basics from 2.12.5 to 2.12.15 (#179, #204, #210).
- Bump github/codeql-action from 2.1.16 to 2.1.27 (#192, #206, #217).
- Bump actions/setup-java from 3.4.1 to 3.6.0 (#195, #202, #218).
- Bump actions/checkout from 3.0.2 to 3.1.0 (#205).
- Bump development java version from temurin-17.0.3+7 to temurin-17.0.4+8 (#212).
- Alphabetically sort `IsoCurrency`, `Holidays` and `BbanStructure` enums entries (#161).
- Add benchmarks that track the evolution of jbanking performances from version to version (#166). Some numbers can be
  seen [here](benchmarks/README.md).
- Increase JVM memory for Maven (#184). Sonar analysis fails with `OutOfMemoryError`.
- Remove bin/check-links (#129). The script has been moved to https://github.com/marcwrobel/checklinks.
- Configure dependabot to check for update of GitHub actions versions (#189).
- Add OpenSSF scorecard badge to the README (#193).
- Update build workflow to use Java 19 instead of Java 18 (#191).
- Use `Character.digit` instead of `Character.getNumericValue` in `IbanCheckDigit` as recommended in
  [Error Prone#CharacterGetNumericValue](https://errorprone.info/bugpattern/CharacterGetNumericValue) (closes #117).
- Add serialization tests for serializable classes (#222).

### Thanks
