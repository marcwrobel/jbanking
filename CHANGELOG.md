All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### Added

- Support numeric characters in BIC's institution code (#452).
  Currently, the BIC validation checks if the institution code contains only letters.
  [ISO 9362:2022 changed this](https://cdn.standards.iteh.ai/samples/84108/21078408ed25469391d0cf6c5d1b6ca9/ISO-9362-2022.pdf) to also allow numeric characters.
- Add currency code XAD/396 to `IsoCurrency` (#488).
  This currency code was introduced by [ISO-4217 amendment number 179](https://www.six-group.com/dam/download/financial-information/data-center/iso-currrency/amendments/dl-currency-iso-amendment-179.pdf) and is effective from 12 May 2025.
  According to the amendment :
  > The Finance Department Arab Monetary Fund (AMF) is applying for new Fund currency code Arab Accounting Dinar with Alphabetic Code XAD and Numeric Code 396.
  > The Arab Accounting Dinar is the official and reporting currency of the Arab Monetary Fund Reserve Asset.
  > The decimal for the currency is set to 1.00, which corresponds to a Minor Unit of 2.
- Add currency code ZWG/924 to `IsoCurrency` (#408).
  This currency code was introduced by [ISO-4217 amendment number 177](https://www.six-group.com/dam/download/financial-information/data-center/iso-currrency/amendments/dl-currency-iso-amendment-177.pdf) and is effective from 25 June 2024.
  According to the amendment :
  > The monetary authority of Zimbabwe (Reserve Bank of Zimbabwe) has confirmed that the new currency ZWG will replace the old currency ZWL.
  > The name of the new currency ZWG will be Zimbabwe Gold with new numeric code 924 and is effective from 25 June 2024.

### Changed

- Add the [National Day of Mourning for former President Jimmy Carter](https://wikipedia.org/wiki/Death_and_state_funeral_of_Jimmy_Carter)
  to the list of holidays of the New York Stock Exchange calendar (#477).

### Fixed

### Deprecated

- Deprecate for removal the cuban convertible peso (CUC) as per ISO-4217 amendment number 178 (#474).
- Deprecate for removal the zimbabwean dollar (ZWL) as per ISO-4217 amendment number 177 (#408).
- Deprecate for removal the sierra leonean leone (SLL) as per ISO-4217 amendment number 175 (#320).

### Removed

### Internal

- Bump com.opengamma.strata:strata-basics from 2.12.28 to 2.12.63 (#376, #379, #383, #386, #392, #396, #405, #412, #453, #481, #493, #499).
- Bump com.google.guava:guava from 32.1.3 to 33.5.0 (#378, #390, #398, #403, #420, #428, #458, #483, #487, #496).
- Bump actions/setup-java from 3.13.0 to v5 (#374, #389, #416, #425, #444, #494).
- Bump actions/cache from 3.3.2 to v4 (#381, #385, #391, #455).
- Bump actions/checkout from 4.1.1 to v5 (#387, #397, #400, #407, #443, #494).
- Bump github/codeql-action from 2.13.4 to v4 (#401, #409, #417, #427, #454, #500).
- Use only major versions for GitHub-managed actions (#480).
- Bump internal Java version from 17.0.8 to 17.0.17 (#479, #501).
- Bump internal Maven version from 3.9.4 to 3.9.9 (#501).

### Thanks

- Fix link in README.md (#393).
