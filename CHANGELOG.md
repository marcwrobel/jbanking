All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### Added

### Changed

- Add [Bank Holiday for the Coronation of King Charles III](https://www.timeanddate.com/holidays/uk/king-coronation-day-holiday)
  to LONDON calendar (#266).

### Fixed

### Deprecated

### Removed

### Internal

- Reactivate SonarCloud analysis for PR originating from this repo (#243).
- Grant permissions to comment pull-requests to SonarCloud (#243).
- Improve caching during SonarCloud analysis (#243).
- Merge codeql and analyze workflows (#243).
- Bump github/codeql-action from 2.1.28 to 2.1.37 (#240, #255, #259, #260, #263, #265, #268).
- Bump actions/setup-java from 3.6.0 to 3.9.0 (#262, #264, #269).
- Bump actions/checkout from 3.1.0 to 3.2.0 (#267).
- Bump actions/cache from 3.0.11 to 3.2.0 (#272).
- Bump parent from 2.7.0 to 2.7.2 (#241, #261).
- Bump strata-basics from 2.12.15 to 2.12.17 (#266, #270).
- Bump development java version from 17.0.4+101 to 17.0.5+8 (#258).
- Bump development maven version from 3.8.5 to 3.8.6 (during #266).
- Enable reproducible builds (#247).
- Add Gitpod integration to facilitate contributions (#251).
- Trigger analysis only if source-related files has been modified (#246).

### Thanks
