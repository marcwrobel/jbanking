All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

### Added

- Officially support Java 17 and 18 (#102).

### Changed

- Update NYSE and New York FED calendars following [Juneteenth National Independence Day Act](https://www.cnbc.com/2021/06/17/juneteenth-federal-holiday-biden-signs-bill.html)
  (#98).

### Fixed

### Deprecated

### Removed

## Internal

- Upgrade to parent [2.7.0](https://github.com/marcwrobel/parent/releases/tag/v2.7.0) (#100).
- Upgrade to strata [2.12.5](https://strata.opengamma.io/releases/) (#96, #99).
- Restrict `GITHUB_TOKEN` permissions in GitHub workflows (#101).
- Add a contribution guide (#101).
- Add the OpenSSF Best Practices badge on the project's README (#101).
- Restrict sonar analysis to main (#106).
- Schedule a sonar analysis every week (#107).
- Prevent duplicate builds on “push” and “pull_request” simultaneous event (#109).
- Fix branch name in `codeql-analysis.yml` workflow (#110).

### Thanks
