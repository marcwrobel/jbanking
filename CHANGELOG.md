All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This new release includes :

### Added

- Categorize ISO currencies (bond market unit, fund, precious metals, National or supranational and other) (#70).

### Changed

- Add platinium jubilee to London calendar (#75).

### Fixed

- Add missing currency UYW (Unidad Previsional) (#69).
- Prevent potential stack overflow in `SwiftPattern` (#71).

### Deprecated

### Removed

## Internal

- Use `maven-enforcer-plugin` to prevent the project to be build with incompatible tools (#72).
- Upgrade to `guava` [30.1](https://github.com/google/guava/releases/tag/v30.1) (#73).
- Upgrade to `strata` [2.9.0](https://github.com/OpenGamma/Strata/releases/tag/v2.9.0) (#74).
- Upgrade to `parent` [2.4.0](https://github.com/marcwrobel/parent/releases/tag/v2.4.0) (#76).

### Thanks
