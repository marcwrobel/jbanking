All notable changes to this project's current version will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

This new release includes :

### Added

- Categorize ISO currencies (bond market unit, fund, precious metals, National or supranational and other) (#70).

### Changed

### Fixed

- Add missing currency UYW (Unidad Previsional) (#69).
- Prevent potential stack overflow in `SwiftPattern` (#71).

### Deprecated

### Removed

## Internal

- Use `maven-enforcer-plugin` to prevent the project to be build with incompatible tools (#72).
- Upgrade to `guava` [30.1](https://github.com/google/guava/releases/tag/v30.1) (#73).

### Thanks
