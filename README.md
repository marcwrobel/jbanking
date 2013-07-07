# jbanking - A Java banking API [![Build Status](https://buildhive.cloudbees.com/job/marcwrobel/job/jbanking/badge/icon)](https://buildhive.cloudbees.com/job/marcwrobel/job/jbanking/)
jbanking is a library of utilities to assist with developing banking functionality.


## Features
jbanking is supporting the following features :
* [ISO 3166-1-alpha-2 country codes](http://wikipedia.org/wiki/ISO_3166-1_alpha-2).
* [ISO 4217 currency codes](http://wikipedia.org/wiki/ISO_4217).
* [ISO 9362:2009 BIC](http://wikipedia.org/wiki/Bank_Identifier_Code) handling and validation.
* [ISO 13616:2007 IBAN](http://wikipedia.org/wiki/International_Bank_Account_Number) handling and validation (for both checkdigit and national bank account number structure).


## Requirements
Any application that uses the jbanking library must use [Java 1.6](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later. No additional dependency required !


## Use it !
You can download the latest build at http://search.maven.org/remotecontent?filepath=fr/marcwrobel/jbanking/1.0/jbanking-1.0.jar or use it as a maven dependency:
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
Read the documentation on the [Wiki](https://github.com/marcwrobel/jbanking/wiki).

Send me an email at [marc.wrobel@gmail.com](mailto:marc.wrobel@gmail.com).

Raise an [issue](https://github.com/marcwrobel/jbanking/issues?sort=created&direction=desc&state=open).


## License
    Copyright 2013 Marc Wrobel (marc.wrobel@gmail.com)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
