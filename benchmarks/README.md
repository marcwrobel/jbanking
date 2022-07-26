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

| Name                                     | Parameter                       | Version        | Ops/s      | Increase |
|------------------------------------------|---------------------------------|----------------|------------|----------|
| b.BicBenchmark.creation                  | AECFFR21                        | 1.0            | 2,460,731  | N/A      |
| b.BicBenchmark.creation                  | AECFFR21                        | 2.1.0          | 2,591,447  | 5.31%    |
| b.BicBenchmark.creation                  | AECFFR21                        | 3.4.0          | 6,861,639  | 164.78%  |
| b.BicBenchmark.creation                  | AECFFR21                        | 4.0.0-SNAPSHOT | 21,039,659 | 206.63%  |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 1.0            | 2,450,359  | N/A      |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 2.1.0          | 2,487,616  | 1.52%    |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 3.4.0          | 6,852,577  | 175.47%  |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 4.0.0-SNAPSHOT | 25,302,021 | 269.23%  |
| b.BicBenchmark.validation                | AECFFR21                        | 1.0            | 2,659,735  | N/A      |
| b.BicBenchmark.validation                | AECFFR21                        | 2.1.0          | 2,819,303  | 6.00%    |
| b.BicBenchmark.validation                | AECFFR21                        | 3.4.0          | 9,241,672  | 227.80%  |
| b.BicBenchmark.validation                | AECFFR21                        | 4.0.0-SNAPSHOT | 39,789,078 | 330.54%  |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 1.0            | 2,644,390  | N/A      |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 2.1.0          | 2,670,018  | 0.97%    |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 3.4.0          | 7,978,573  | 198.82%  |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 4.0.0-SNAPSHOT | 37,336,445 | 367.96%  |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 3.4.0          | 1,436,109  | N/A      |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 4.0.0-SNAPSHOT | 1,497,954  | 4.31%    |
| b.CalendarBenchmark.validation           | PARIS                           | 4.0.0-SNAPSHOT | 2,665,496  | N/A      |
| b.CalendarBenchmark.validation           | PARIS                           | 3.4.0          | 2,689,448  | 0.90%    |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 3.4.0          | 20,483,692 | N/A      |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 4.0.0-SNAPSHOT | 20,883,830 | 1.95%    |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 2.1.0          | 1,155,706  | N/A      |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 3.4.0          | 1,442,163  | 24.79%   |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 4.0.0-SNAPSHOT | 2,002,731  | 38.87%   |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 2.1.0          | 1,328,852  | N/A      |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 3.4.0          | 1,973,522  | 48.51%   |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 4.0.0-SNAPSHOT | 2,725,699  | 38.11%   |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 2.1.0          | 618,766    | N/A      |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 3.4.0          | 1,236,274  | 99.80%   |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 4.0.0-SNAPSHOT | 1,634,802  | 32.24%   |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 2.1.0          | 1,134,536  | N/A      |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 3.4.0          | 1,487,712  | 31.13%   |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 4.0.0-SNAPSHOT | 2,049,455  | 37.76%   |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 2.1.0          | 1,369,866  | N/A      |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 3.4.0          | 2,023,290  | 47.70%   |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 4.0.0-SNAPSHOT | 2,743,085  | 35.58%   |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 2.1.0          | 634,193    | N/A      |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 3.4.0          | 1,223,625  | 92.94%   |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 4.0.0-SNAPSHOT | 1,869,288  | 52.77%   |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 2.1.0          | 489,264    | N/A      |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 1.0            | 546,745    | 11.75%   |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 4.0.0-SNAPSHOT | 1,088,098  | 99.01%   |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 3.4.0          | 1,159,728  | 6.58%    |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 2.1.0          | 508,675    | N/A      |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 1.0            | 559,173    | 9.93%    |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 3.4.0          | 841,874    | 50.56%   |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 4.0.0-SNAPSHOT | 856,814    | 1.77%    |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 2.1.0          | 632,637    | N/A      |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 1.0            | 657,696    | 3.96%    |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 4.0.0-SNAPSHOT | 1,388,058  | 111.05%  |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 3.4.0          | 1,424,889  | 2.65%    |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 2.1.0          | 501,785    | N/A      |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 1.0            | 508,206    | 1.28%    |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 3.4.0          | 1,054,861  | 107.57%  |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 4.0.0-SNAPSHOT | 1,154,262  | 9.42%    |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 2.1.0          | 515,006    | N/A      |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 1.0            | 530,957    | 3.10%    |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 3.4.0          | 871,960    | 64.22%   |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 4.0.0-SNAPSHOT | 928,907    | 6.53%    |
| b.IbanBenchmark.validation               | NO2451742753161                 | 2.1.0          | 625,019    | N/A      |
| b.IbanBenchmark.validation               | NO2451742753161                 | 1.0            | 650,438    | 4.07%    |
| b.IbanBenchmark.validation               | NO2451742753161                 | 3.4.0          | 1,409,987  | 116.78%  |
| b.IbanBenchmark.validation               | NO2451742753161                 | 4.0.0-SNAPSHOT | 1,419,185  | 0.65%    |
