jbanking-benchmarks allows us to track the evolution of jbanking performances from version to version. To achieve that
goal jbanking-benchmarks is using [Java Microbenchmark Harness (JMH)](https://github.com/openjdk/jmh) and the
[JDK Flight Recorder profiler](https://github.com/openjdk/jmc) (and
the [JDK Mission Control](https://github.com/openjdk/jmc) to analyze the results).

Here are the benchmarks that are available :

- Iban (creation and validation, with different IBAN sizes),
- Creditor identifiers (creation and validation, with different CI sizes),
- BIC (creation and validation, for BIC8 and BIC11),
- Calendars (check if a date is a business day).

# Run the benchmarks

First install the current SNAPSHOT version of jbanking in you local Maven repository. Then use the [`run`](/run)
script: `./run`.

By default, the script run the benchmarks against the latest of each major version and produces, in a temporary
directory (e.g. `/tmp/jbanking-benchmarks.85iJgvVVOc`), a file named `jbanking-benchmarks.md`. This files contains the
aggregated results of all the benchmarks processed, sorted and formatted as a Markdown table.

The JFR files for each version and each test can be found in subdirectories
(e.g. `2.1.0/b.BicBenchmark.isValid-Throughput-value-AECFFR21/profile.jfr`) of the temporary directory.

Options are available to target only specific versions or benchmarks. See `./run --help` for more information.

# Latest results

All test were run with in the following conditions :

- [AMD Ryzen 7 4800H](https://www.amd.com/fr/products/apu/amd-ryzen-7-4800h),
- JDK 1.8.0_332, OpenJDK 64-Bit Server VM ([Eclipse Temurin build](https://adoptium.net/temurin/releases/)),
- 128 MB of heap memory,
- 3 warmup iterations,
- 3 iterations,
- 10 seconds per iteration,
- 1 thread,
- blackhole mode: full + dont-inline hint.

| Name                                     | Parameter                       | Version        |      Ops/s | Increase |
|------------------------------------------|---------------------------------|----------------|-----------:|---------:|
| b.BicBenchmark.creation                  | AECFFR21                        | 1.0            |  2,444,715 |      N/A |
| b.BicBenchmark.creation                  | AECFFR21                        | 2.1.0          |  2,481,546 |   +1.48% |
| b.BicBenchmark.creation                  | AECFFR21                        | 3.4.0          |  6,649,405 |  +62.68% |
| b.BicBenchmark.creation                  | AECFFR21                        | 4.0.0-SNAPSHOT | 19,604,576 | +194.83% |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 1.0            |  2,512,454 |      N/A |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 2.1.0          |  2,550,922 |   +1.51% |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 3.4.0          |  8,066,353 |  +216.21 |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 4.0.0-SNAPSHOT | 23,230,205 |  +187.98 |
| b.BicBenchmark.validation                | AECFFR21                        | 1.0            |  2,701,550 |      N/A |
| b.BicBenchmark.validation                | AECFFR21                        | 2.1.0          |  2,831,007 |   +4.57% |
| b.BicBenchmark.validation                | AECFFR21                        | 3.4.0          |  8,874,888 | +302.85% |
| b.BicBenchmark.validation                | AECFFR21                        | 4.0.0-SNAPSHOT | 35,753,064 |  309.83% |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 2.1.0          |  2,527,600 |      N/A |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 1.0            |  2,676,293 |   +5.56% |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 3.4.0          |  7,797,982 | +191.37% |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 4.0.0-SNAPSHOT | 32,069,027 | +311.24% |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 4.0.0-SNAPSHOT |  1,464,062 |      N/A |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 3.4.0          |  1,508,236 |   +2.93% |
| b.CalendarBenchmark.validation           | PARIS                           | 3.4.0          |  2,703,850 |      N/A |
| b.CalendarBenchmark.validation           | PARIS                           | 4.0.0-SNAPSHOT |  2,710,297 |   +0.24% |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 3.4.0          | 20,365,368 |      N/A |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 4.0.0-SNAPSHOT | 21,186,208 |   +3.87% |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 2.1.0          |  1,179,963 |      N/A |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 4.0.0-SNAPSHOT |  1,339,117 |  +11.89% |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 3.4.0          |  1,473,817 |   +9.14% |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 2.1.0          |  1,353,862 |      N/A |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 4.0.0-SNAPSHOT |  1,982,338 |  +31.70% |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 3.4.0          |  2,006,483 |   +1.20% |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 2.1.0          |    650,804 |      N/A |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 4.0.0-SNAPSHOT |  1,269,066 |  +48.72% |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 3.4.0          |  1,293,270 |   +1.87% |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 2.1.0          |  1,171,900 |      N/A |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 3.4.0          |  1,485,479 |  +21.11% |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 4.0.0-SNAPSHOT |  1,525,590 |   +2.63% |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 2.1.0          |    1359341 |      N/A |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 4.0.0-SNAPSHOT |  2,022,577 |  +32.79% |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 3.4.0          |  2,044,620 |   +1.08% |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 2.1.0          |    647,807 |      N/A |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 4.0.0-SNAPSHOT |  1,242,825 |  +47.88% |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 3.4.0          |  1,283,238 |   +3.15% |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 2.1.0          |    503,185 |      N/A |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 1.0            |    537,262 |   +6.34% |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 4.0.0-SNAPSHOT |  1,106,781 |  +51.46% |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 3.4.0          |  1,130,385 |   +2.09% |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 2.1.0          |    515,480 |      N/A |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 1.0            |    533,216 |   +3.33% |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 3.4.0          |    897,188 |  +40.57% |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 4.0.0-SNAPSHOT |    956,700 |   +6.22% |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 2.1.0          |    628,450 |      N/A |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 1.0            |    655,568 |   +4.14% |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 3.4.0          |  1,388,082 |  +52.77% |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 4.0.0-SNAPSHOT |  1,468,227 |   +5.46% |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 2.1.0          |    502,608 |      N/A |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 1.0            |    511,613 |   +1.76% |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 3.4.0          |  1,064,568 |  +51.94% |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 4.0.0-SNAPSHOT |  1,108,403 |   +3.95% |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 2.1.0          |    523,769 |      N/A |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 1.0            |    532,346 |   +1.61% |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 4.0.0-SNAPSHOT |    879,841 |  +39.50% |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 3.4.0          |    902,781 |   +2.54% |
| b.IbanBenchmark.validation               | NO2451742753161                 | 2.1.0          |    622,808 |      N/A |
| b.IbanBenchmark.validation               | NO2451742753161                 | 1.0            |    670,624 |   +7.13% |
| b.IbanBenchmark.validation               | NO2451742753161                 | 3.4.0          |  1,448,533 |  +53.70% |
| b.IbanBenchmark.validation               | NO2451742753161                 | 4.0.0-SNAPSHOT |  1,484,615 |   +2.43% |
