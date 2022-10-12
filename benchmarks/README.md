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
script with a JDK 8 in your path: `./run`.

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

| Name                                     | Parameter                       | Version        | Ops/s      | Increase |
|------------------------------------------|---------------------------------|----------------|------------|----------|
| b.BicBenchmark.creation                  | AECFFR21                        | 2.1.0          | 2,494,767  | N/A      |
| b.BicBenchmark.creation                  | AECFFR21                        | 3.4.0          | 7,069,750  | 183.38%  |
| b.BicBenchmark.creation                  | AECFFR21                        | 4.0.0-SNAPSHOT | 19,627,075 | 177.62%  |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 2.1.0          | 2,449,964  | N/A      |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 3.4.0          | 5,972,033  | 143.76%  |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 4.0.0-SNAPSHOT | 23,075,096 | 286.39%  |
| b.BicBenchmark.validation                | AECFFR21                        | 2.1.0          | 2,724,489  | N/A      |
| b.BicBenchmark.validation                | AECFFR21                        | 3.4.0          | 8,997,413  | 230.24%  |
| b.BicBenchmark.validation                | AECFFR21                        | 4.0.0-SNAPSHOT | 40,080,401 | 345.47%  |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 2.1.0          | 2,667,664  | N/A      |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 3.4.0          | 7,911,761  | 196.58%  |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 4.0.0-SNAPSHOT | 37,468,171 | 373.58%  |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 3.4.0          | 1,488,633  | N/A      |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 4.0.0-SNAPSHOT | 1,526,671  | 2.56%    |
| b.CalendarBenchmark.validation           | PARIS                           | 3.4.0          | 2,644,530  | N/A      |
| b.CalendarBenchmark.validation           | PARIS                           | 4.0.0-SNAPSHOT | 2,748,954  | 3.95%    |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 3.4.0          | 20,497,110 | N/A      |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 4.0.0-SNAPSHOT | 21,279,043 | 3.81%    |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 2.1.0          | 1,119,670  | N/A      |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 3.4.0          | 1,442,878  | 28.87%   |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 4.0.0-SNAPSHOT | 2,038,073  | 41.25%   |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 2.1.0          | 1,341,951  | N/A      |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 3.4.0          | 1,844,161  | 37.42%   |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 4.0.0-SNAPSHOT | 2,767,084  | 50.05%   |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 2.1.0          | 630,729    | N/A      |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 3.4.0          | 1,235,489  | 95.88%   |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 4.0.0-SNAPSHOT | 1,832,649  | 48.33%   |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 2.1.0          | 1,194,017  | N/A      |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 3.4.0          | 1,422,717  | 19.15%   |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 4.0.0-SNAPSHOT | 2,010,936  | 41.34%   |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 2.1.0          | 1,336,997  | N/A      |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 3.4.0          | 1,949,789  | 45.83%   |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 4.0.0-SNAPSHOT | 2,759,193  | 41.51%   |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 2.1.0          | 645,446    | N/A      |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 3.4.0          | 1,262,005  | 95.52%   |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 4.0.0-SNAPSHOT | 1,817,370  | 44.01%   |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 2.1.0          | 503,462    | N/A      |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 3.4.0          | 1,080,801  | 114.67%  |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 4.0.0-SNAPSHOT | 4,591,815  | 324.85%  |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 2.1.0          | 501,694    | N/A      |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 3.4.0          | 872,346    | 73.88%   |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 4.0.0-SNAPSHOT | 3,093,770  | 254.65%  |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 2.1.0          | 634,614    | N/A      |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 3.4.0          | 1,308,199  | 106.14%  |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 4.0.0-SNAPSHOT | 6,851,562  | 423.74%  |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 2.1.0          | 491,451    | N/A      |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 3.4.0          | 1,090,326  | 121.86%  |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 4.0.0-SNAPSHOT | 4,417,231  | 305.13%  |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 2.1.0          | 519,594    | N/A      |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 3.4.0          | 902,313    | 73.66%   |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 4.0.0-SNAPSHOT | 3,273,056  | 262.74%  |
| b.IbanBenchmark.validation               | NO2451742753161                 | 2.1.0          | 619,001    | N/A      |
| b.IbanBenchmark.validation               | NO2451742753161                 | 3.4.0          | 1,379,917  | 122.93%  |
| b.IbanBenchmark.validation               | NO2451742753161                 | 4.0.0-SNAPSHOT | 7,028,038  | 409.31%  |
