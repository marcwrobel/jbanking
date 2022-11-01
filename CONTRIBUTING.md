# Contribution guide

Want to contribute? Great! We try to make it easy, and all contributions, even the smaller ones, are more than welcome.
This includes bug reports, fixes, documentation, examples... But first, read this page.

## Legal

All original contributions to jbanking are licensed under the ASL - Apache License, version 2.0 or later.

All contributions are subject to the Developer Certificate of Origin (DCO). The DCO text is also included verbatim in
the [dco.txt](/dco.txt) file in the root directory of the repository.

## Code of conduct

In the interest of fostering an open and welcoming environment, jbanking team members and contributors are expected to
follow our [code of conduct](/CODE_OF_CONDUCT.md).

## Reporting an issue

This project uses [GitHub issues](https://docs.github.com/en/issues) to manage the issues. Open an issue directly in
GitHub.

If you found a bug, please use the _Bug report_ template and follow the indications.

## Developer requirements

Before you start developing on jbanking you will need to :

1. install Git,
2. install a JDK 11+ (for instance [Eclipse Temurin™](https://adoptium.net/temurin/releases)),
3. install [Maven 3.8+](https://maven.apache.org/download.cgi).

It is recommended to install the JDK and Maven using tools like [asdf](https://asdf-vm.com/guide/getting-started.html)
or [SDKMan!](https://sdkman.io/). Note that an asdf [.tool-versions](/.tool-versions) file that list the required JDK
and Maven versions can be found in the root directory of the repository.

Also, make sure you have set up your Git authorship correctly:

```bash
git config --global user.name "Your Full Name"
git config --global user.email your.email@example.com
```

## Code Style

jbanking has a strictly enforced code style. Code formatting is done by the
[`formatter-maven-plugin`](https://github.com/revelc/formatter-maven-plugin). This maven plugin formats code using the
Eclipse code formatter.

Code is automatically formatted each time you run `mvn`. You can also run it manually :

```shell
mvn formatter:validate # Validate code format
mvn formatter:format # Reformat code
```

If possible, you are encouraged to configure your IDE to format files with the
project [eclipse-formatter-config.xml](/eclipse-formatter-config.xml).

## Building and testing

Just execute the following commands:

```shell
git clone git@github.com:marcwrobel/jbanking.git
cd jbanking
mvn verify
```

Wait for a bit and you're done.

## Benchmarking

In order to track the evolution of jbanking performances from version to version, some benchmarks
[are available here](/benchmarks/README.md).

## Releasing

In order to publish a release to Maven Central, you :

- must be a listed collaborator on [`marcwrobel/jbanking`](https://github.com/marcwrobel/jbanking),
- must have an account on the [Sonatype Jira issue tracker](https://issues.sonatype.org),
- need to declare, in your Maven `settings.xml`, the `oss.sonatype.org` profile and the `sonatype-nexus-staging` server
  (you can find those declarations in [this sample settings.xml](/.mvn/build-settings.xml)),

Then you can start the release process. First disable the [`main` branch protection
rules](https://github.com/marcwrobel/jbanking/settings/branches) _Require a pull request before merging_ and _Require
status checks to pass before merging_.

Then execute the following commands:

```shell
cd jbanking
mvn release:prepare -Prelease
mvn release:perform -Prelease
```

If everything went fine, go to [oss.sonatype.org](https://oss.sonatype.org/) and close/release the staging repository.
Details are available on the [OSSRH publish guide](https://central.sonatype.org/publish/publish-guide/).

And finally:

1. re-enable the disabled [branch protection rules](https://github.com/marcwrobel/jbanking/settings/branches),
2. declare the release on GitHub :
   1. copy the [changelog content](/CHANGELOG.md) in the release notes,
   2. check _Set as the latest release_ if appropriate,
   3. and check _Create a discussion for this release_  to automatically create the [release
      discussion](https://github.com/marcwrobel/jbanking/discussions)),
3. empty the [changelog](/CHANGELOG.md) for the next release,
4. update jbanking version in [README.md](/README.md),
5. lock and unpin the [discussion](https://github.com/marcwrobel/jbanking/discussions) that relates to the previous
   version,
6. pin the [discussion](https://github.com/marcwrobel/jbanking/discussions) that relates to the current version.
7. close the [milestone](https://github.com/marcwrobel/jbanking/milestones) that was created for the released version.

## Continuous Integration

Because we are all humans, and to ensure jbanking is stable for everyone, all changes must go through jbanking
continuous integration. jbanking CI is based on GitHub Actions, which means that everyone has the ability to
automatically execute CI in their forks as part of the process of making changes (except maybe the Sonarcloud and
Sonatype lift analysis).

jbanking CI checks are:

1. code is properly formatted,
2. project builds,
3. project test suite is passing,
4. javadoc can be generated,
5. [SonarCloud](https://sonarcloud.io/project/overview?id=fr.marcwrobel:jbanking) analysis,
6. [GitHub CodeQL](https://codeql.github.com/) analysis,
7. [Sonatype lift](https://lift.sonatype.com/) analysis.

CI checks are automatically triggered on PR and on push on master or maintenance branches. All workflows are also
automatically triggered weekly.

## Contribution rules

To contribute, use a GitHub pull requests from your own fork. You can find more information on GitHub's
documentation [Contributing to projects](https://docs.github.com/en/get-started/quickstart/contributing-to-projects).

### Code reviews

All submissions need to be reviewed by at least one jbanking committer before being merged.
[GitHub Pull Request Review Process](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/reviewing-changes-in-pull-requests/about-pull-request-reviews)
is followed for every pull request.

### Coding Guidelines

- Commits should be atomic and semantic. Please properly squash your pull requests before submitting them. Fixup commits
  can be used temporarily during the review process but things should be squashed at the end to have meaningful commits.
- Git authorship (i.e. git `user.name` and `user.email`) must be properly set.
- Tests and documentation are not optional. Don't forget to include tests in your pull requests. Also don't forget the
  documentation (reference documentation, javadoc...).
- Make sure to launch the full test suite before creating your pull request.
- `@author` tags are disallowed in the javadoc: they are hard to maintain, and we use the Git history to track
  authorship. GitHub also has [this nice page](https://github.com/marcwrobel/jbanking/graphs/contributors) with your
  contributions.
- Do not add runtime dependencies to jbanking.

## Security

jbanking does not have any runtime dependency. But it relies on some external libraries for testing purpose (such as
[guava](https://github.com/google/guava), [nv-i18n](https://github.com/TakahikoKawasaki/nv-i18n) and
[OpenGamma Strata](https://github.com/OpenGamma/Strata)). In order to keep those libraries up-to-date we are using
[Dependabot](https://docs.github.com/en/code-security/dependabot).

jbanking is also following the [OpenSSF Best Practices](https://bestpractices.coreinfrastructure.org/en/projects/6217).
