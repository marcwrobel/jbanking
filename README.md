[![Build](https://github.com/marcwrobel/jbanking/workflows/build/badge.svg)](https://github.com/marcwrobel/jbanking/actions)
[![Supported JVM Versions](https://img.shields.io/badge/JVM-8--17-brightgreen.svg?logo=openjdk)](https://github.com/marcwrobel/jbanking/actions/workflows/build.yml)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=fr.marcwrobel:jbanking&metric=alert_status)](https://sonarcloud.io/dashboard?id=fr.marcwrobel:jbanking)
[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=fr.marcwrobel:jbanking&metric=coverage)](https://sonarcloud.io/dashboard?id=fr.marcwrobel:jbanking)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=fr.marcwrobel:jbanking&metric=bugs)](https://sonarcloud.io/dashboard?id=fr.marcwrobel:jbanking)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=fr.marcwrobel:jbanking&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=fr.marcwrobel:jbanking)
[![Maven Central](https://img.shields.io/maven-central/v/fr.marcwrobel/jbanking.svg?logo=apache-maven&label=Maven%20Central)](https://search.maven.org/search?q=g:%22fr.marcwrobel%22%20AND%20a:%22jbanking%22)
[![javadoc](https://javadoc.io/badge2/fr.marcwrobel/jbanking/javadoc.svg)](https://javadoc.io/doc/fr.marcwrobel/jbanking)
[![OpenSSF Best Practices](https://bestpractices.coreinfrastructure.org/projects/6217/badge)](https://bestpractices.coreinfrastructure.org/projects/6217)

# jbanking - A Java banking API

jbanking is a library of utilities to assist with developing banking functionalities. jbanking is focused on, but not
limited to, European banking.

## Features

jbanking is supporting the following features :

* [ISO 3166-1 countries](https://wikipedia.org/wiki/ISO_3166-1) with alpha-2 code, alpha-3 code, numeric code and status,
  e.g. independent or dependent (according to the [International Organization for Standardization](https://www.iso.org))
  .
* Countries or territories participation to economic agreements :
  * [European Economic Area (EEA)](https://wikipedia.org/wiki/European_Economic_Area),
  * [European Free Trade Association (EFTA)](https://wikipedia.org/wiki/European_Free_Trade_Association),
  * [European Union (EU)](https://en.wikipedia.org/wiki/European_Union),
  * [SEPA COM Pacifique](https://www.cfonb.org/Default.aspx?lid=1&rid=122&rvid=239),
  * [Single Euro Payments Area (SEPA)](https://wikipedia.org/wiki/Single_Euro_Payments_Area).
* [ISO 4217 currencies](https://wikipedia.org/wiki/ISO_4217) (with alphabetic code, numeric code, minor unit and
  countries using it).
* [ISO 9362:2009 BIC](https://wikipedia.org/wiki/Bank_Identifier_Code) handling and validation.
* [ISO 13616:2007 IBAN](https://wikipedia.org/wiki/International_Bank_Account_Number) handling and validation (for both
  check digit and national bank account number structure).
* [Creditor Identifiers (CIs)](https://www.europeanpaymentscouncil.eu/document-library/guidance-documents/creditor-identifier-overview)
  handling and validation.
* Configurable [holiday](https://wikipedia.org/wiki/Holiday) calendar support with predefined calendars for :
  * Frankfurt, London, Paris, Sydney, and Tokyo financial districts,
  * Federal Reserve Bank of New York (FED),
  * New York Stock Exchange (NYSE),
  * European Union TARGET system.

## Requirements

jbanking requires at least [Java 8](https://adoptium.net/) (build is tested against Java 8, 11, 17 and 18). It has no
additional dependency !

## Use it !

You can download the latest build on [Maven Central](https://search.maven.org/artifact/fr.marcwrobel/jbanking) or use it
as a maven dependency:

```xml

<dependency>
  <groupId>fr.marcwrobel</groupId>
  <artifactId>jbanking</artifactId>
  <version>3.3.0</version>
</dependency>
```

Then you just have to use the jbanking API.

```java
// Get ISO country information
IsoCountry country = IsoCountry.fromAlpha2Code("FR").get();
Assertion.assertEquals("FRA", country.getAlpha3Code());
Assertion.assertEquals(250, country.getNumericCode());
Assertion.assertTrue(country.isIndependent());
Assertion.assertTrue(country.isParticipatingTo(EUROPEAN_ECONOMIC_AREA));

// Get ISO currency information
IsoCurrency currency = IsoCurrency.fromAlphabeticCode("EUR").get();
Assertion.assertEquals(978, currency.getNumericCode());
Assertion.assertEquals(2, currency.getMinorUnit().get());
Assertion.assertEquals(NATIONAL, currency.getCategory());
Assertion.assertTrue(currency.getCountries().contains(FR));

// Validate an IBAN
Assertions.assertTrue(Iban.isValid("FR2531682128768051490609537"));

// Get IBAN information
Iban iban = new Iban("fr2531682128768051490609537");
Assertions.assertEquals("FR2531682128768051490609537", iban.toString());
Assertions.assertEquals("FR", iban.getCountryCode());
Assertions.assertEquals("25", iban.getCheckDigit());
Assertions.assertEquals("31682128768051490609537", iban.getBban());
Assertions.assertEquals("FR25 3168 2128 7680 5149 0609 537", iban.toPrintableString());

// Validate a BIC
Assertions.assertTrue(Bic.isValid("PSSTFRPPXXX"));

// Get BIC information
Bic bic = new Bic("psstfrppxxx");
Assertions.assertEquals("PSSTFRPPXXX", bic.toString());
Assertions.assertEquals("PSST", bic.getInstitutionCode());
Assertions.assertEquals("FR", bic.getCountryCode());
Assertions.assertEquals("PP", bic.getLocationCode());
Assertions.assertEquals("XXX", bic.getBranchCode());
Assertions.assertTrue(bic.isLiveBic());

// Validate a creditor identifier
Assertions.assertTrue(CreditorIdentifier.isValid("FR72ZZZ123456"));

// Get creditor identifier information
CreditorIdentifier ci = new CreditorIdentifier("fr72zzz123456");
Assertions.assertEquals("FR72ZZZ123456", ci.toString());
Assertions.assertEquals("FR", ci.getCountryCode());
Assertions.assertEquals("72", ci.getCheckDigit());
Assertions.assertEquals("ZZZ", ci.getBusinessCode());
Assertions.assertEquals("123456", ci.getNationalIdentifier());

// Using predefined calendars
Calendar calendar = FinancialCalendars.PARIS;
Assertion.assertTrue(calendar.isHoliday(LocalDate.of(2022, 1, 1)));
Assertion.assertFalse(calendar.isBusinessDay(LocalDate.of(2022, 1, 1)));
Assertion.assertEquals(LocalDate.of(2022, 1, 3), calendar.next(LocalDate.of(2022, 1, 1)));
Assertion.assertEquals(Arrays.asList(LocalDate.of(2022, 1, 3), LocalDate.of(2022, 1, 4)), calendar.businessDaysWithin(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 4)));
```

For more information take a look at [the javadoc](https://javadoc.io/doc/fr.marcwrobel/jbanking) and
[the unit tests](/src/test/java/fr/marcwrobel/jbanking). Changelogs are available on
[GitHub Releases](https://github.com/marcwrobel/jbanking/releases).

## Contribute

Take a look at the [contribution guide](CONTRIBUTING.md).

## Need help ?

Start a [discussion](https://github.com/marcwrobel/jbanking/discussions),
raise an [issue](https://github.com/marcwrobel/jbanking/issues?sort=created&direction=desc&state=open)
or contribute with [a pull-request](https://github.com/marcwrobel/jbanking/pulls) (please take a look at the
[contribution guide](CONTRIBUTING.md) before).

And for the things that must be kept private (**only** !), such as [security issues](/SECURITY.md), email me at
[marc.wrobel@gmail.com](mailto:marc.wrobel@gmail.com).
