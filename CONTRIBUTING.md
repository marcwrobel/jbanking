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
[`git-code-format-maven-plugin`](https://github.com/Cosium/git-code-format-maven-plugin), a maven plugin that
automatically deploys [google-java-format](https://github.com/google/google-java-format) code formatter as a pre-commit
git hook.

If possible, you are encouraged to configure your IDE to format files with
[Google Java Style](https://google.github.io/styleguide/javaguide.html). Instruction for Eclipse and Intellij IDEA can
be found on [google/google-java-format](https://github.com/google/google-java-format).

## Building and testing main

Just do the following:

```bash
git clone git@github.com:marcwrobel/jbanking.git
cd jbanking
mvn verify
```

Wait for a bit and you're done.

## Contribution rules

To contribute, use a GitHub pull requests from your own fork. You can find more information on GitHub's
documentation [Contributing to projects](https://docs.github.com/en/get-started/quickstart/contributing-to-projects).

### Code reviews

All submissions need to be reviewed by at least one jbanking committer before being merged.
[GitHub Pull Request Review Process](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/reviewing-changes-in-pull-requests/about-pull-request-reviews)
is followed for every pull request.

### Continuous Integration

Because we are all humans, and to ensure jbanking is stable for everyone, all changes must go through jbanking
continuous integration. jbanking CI is based on GitHub Actions, which means that everyone has the ability to
automatically execute CI in their forks as part of the process of making changes.

### Coding Guidelines

- Commits should be atomic and semantic. Please properly squash your pull requests before submitting them. Fixup commits
  can be used temporarily during the review process but things should be squashed at the end to have meaningful commits.
- Tests and documentation are not optional. Don't forget to include tests in your pull requests. Also don't forget the
  documentation (reference documentation, javadoc...).
- Make sure to launch the full tests suite before creating your pull request.
- `@author` tags are disallowed in the javadoc: they are hard to maintain, and we use the Git history to track
  authorship. GitHub also has [this nice page](https://github.com/marcwrobel/jbanking/graphs/contributors) with your
  contributions.
