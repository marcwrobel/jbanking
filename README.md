[![Build](https://github.com/marcwrobel/jbanking/workflows/build/badge.svg)](https://github.com/marcwrobel/jbanking/actions)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=fr.marcwrobel:jbanking&metric=alert_status)](https://sonarcloud.io/dashboard?id=fr.marcwrobel:jbanking)
[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=fr.marcwrobel:jbanking&metric=coverage)](https://sonarcloud.io/dashboard?id=fr.marcwrobel:jbanking)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=fr.marcwrobel:jbanking&metric=bugs)](https://sonarcloud.io/dashboard?id=fr.marcwrobel:jbanking)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=fr.marcwrobel:jbanking&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=fr.marcwrobel:jbanking)

# jbanking - A Java banking API
jbanking is a library of utilities to assist with developing banking functionality.


## Features
jbanking is supporting the following features :
* [ISO 3166-1-alpha-2 country codes](http://wikipedia.org/wiki/ISO_3166-1_alpha-2).
* [ISO 4217 currency codes](http://wikipedia.org/wiki/ISO_4217).
* [ISO 9362:2009 BIC](http://wikipedia.org/wiki/Bank_Identifier_Code) handling and validation.
* [ISO 13616:2007 IBAN](http://wikipedia.org/wiki/International_Bank_Account_Number) handling and
  validation (for both checkdigit and national bank account number structure).


## Requirements
Any application that uses the jbanking library must use [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
or later. No additional dependency required !


## Use it !
You can download the latest build on [Maven Central](https://search.maven.org/artifact/fr.marcwrobel/jbanking)
or use it as a maven dependency:
```xml
<dependency>
    <groupId>fr.marcwrobel</groupId>
    <artifactId>jbanking</artifactId>
    <version>1.0</version>
</dependency>
```

For usage examples check the tests.


## Releases
Take a look at the [changelog](src/etc/changelog.md)


## Need help ?
Read the [javadoc](src/main/java/fr/marcwrobel/jbanking) and take a look at the [unit tests](src/test/java/fr/marcwrobel/jbanking).

Raise an [issue](https://github.com/marcwrobel/jbanking/issues?sort=created&direction=desc&state=open).

Send me an email at [marc.wrobel@gmail.com](mailto:marc.wrobel@gmail.com).
