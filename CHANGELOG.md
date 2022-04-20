All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This new release…

### Added

- Add currency code VED/926 to `IsoCurrency` (#85). This currency code was introduced by
  [ISO-4217 amendment number 170](https://www.currency-iso.org/dam/downloads/dl_currency_iso_amendment_170.pdf) and is
  effective from 1 October 2021. According to the amendment :
  > The Bolívar Soberano (VES) is redenominated by removing six zeros from the denominations. A new currency code
  > VED/926 representing the new valuation (1,000,000 times old VES/928) is introduced on 1 October 2021 for any
  > internal needs during the redenomination process, but is not replacing VES as the official currency code. The
  > Central Bank of Venezuela will not adopt the new codes in the local system, VES/928 remains in use.
  >
  > The actual currency code VES/928 remains the valid code after 1 October 2021 to use in any future
  > transactions to indicate the redenominated Bolívar Soberano.
- Add currency code SLE/925 to `IsoCurrency` (#86). This currency code was introduced by
  [ISO-4217 amendment number 171](https://www.currency-iso.org/dam/downloads/dl_currency_iso_amendment_171.pdf) and is
  effective from 1 April 2022. According to the amendment :
  > The Sierra Leonean LEONE (SLL) is redenominated by removing three (3) zeros from the denominations. A new currency
  > code SLE/925 representing the new valuation  (1’000 times old SLL/694) is introduced on 1 st April 2022 for any
  > internal needs during the redenomination process, and is replacing SLL as the official currency code, after the
  > transition period to be determined.
  >
  > During this transition period, both the old Leone and new Leone will be in physical circulation for at least 90
  > days.
  >
  > The Bank of Sierra Leone will adopt the new code in the local system but SLL/694 shall remain in use until further
  > notice.
  >
  > The Sierra Leonean currency shall continue to be the LEONE and this will not change after redenomination.

### Changed

### Fixed

- Fixed typos.

### Deprecated

### Removed

## Internal

- Upgrade to guava [31.1-jre](https://github.com/google/guava/releases/tag/v31.1) (#86).
- Upgrade to nv-i18n [1.29](https://github.com/TakahikoKawasaki/nv-i18n/blob/master/CHANGES.md#129-2021-08-26) (#87).
- Upgrade to strata [2.11.6](https://github.com/OpenGamma/Strata/releases/tag/v2.11.6) (#88).
- Upgrade to parent [2.6.0](https://github.com/marcwrobel/parent/releases/tag/v2.6.0) (#91).

### Thanks
