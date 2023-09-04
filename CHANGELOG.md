All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### Added

- Support random IBAN generation based on countries (`RandomIban.next(IsoCountry...)`), country alpha-2
  codes (`RandomIban.next(String...)`) or currencies (`RandomIban.next(IsoCurrency...)`) (#339).
- Support random BIC generation based on countries (`RandomBic.next(IsoCountry...)`), country alpha-2
  codes (`RandomBic.next(String...)`) or currencies (`RandomBic.next(IsoCurrency...)`) (#338).

### Changed

- Make `Bic` constants public (during #339). New available constants are `BIC8_LENGTH`,
  `BIC11_LENGTH`, `INSTITUTION_CODE_INDEX`, `INSTITUTION_CODE_LENGTH`, `COUNTRY_CODE_INDEX`,
  `COUNTRY_CODE_LENGTH`, `LOCATION_CODE_INDEX`, `LOCATION_CODE_LENGTH`, `BRANCH_CODE_INDEX`,
  `BRANCH_CODE_LENGTH`.

### Fixed

### Deprecated

### Removed

### Internal

- Comment GitHub actions with version (#287).
- Bump actions/cache from 3.2.2 to 3.3.1 (#284, #294, #301, #305, #308, #309).
- Bump actions/setup-java from 3.9.0 to 3.12.0 (#299, #318, #356).
- Bump actions/checkout from 3.3.0 to 4.0.0 (#313, #316, #324, #352, #362, #363).
- Bump github/codeql-action from 2.1.37 to 2.13.4 (#286, #290, #291, #295, #300, #302, #307, #310,
  #312, #315, #317, #321, #322, #325, #326, #328, #329, #335, #348, #353).
- Bump cyclonedx-maven-plugin from 2.7.4 to 2.7.5 (#304).
- Bump strata-basics from 2.12.17 to 2.12.28 (#285, #288, #289, #311, #330, #332, #333, #347, #350, #358).
- Bump parent from 3.0.0 to 3.0.2 (#306, #331).
- Bump maven from 3.8.6 to 3.9.0 in `.tool-versions` (#306).
- Update build workflow to use Java 20 instead of Java 19 (#336).
- Bump java from 17.0.5 to 17.0.8 (#340, #361).
- Bump maven from 3.9.0 to 3.9.4 (#340, #361).
- Bump guava from 31.1-jre to 32.1.2-jre (#345, #351, #355, #357).
- Update benchmarks results (#341).
- Migrate tests to AssertJ (#359).
- Upgrade analyze workflow to use Java 17 (#361).

### Thanks
