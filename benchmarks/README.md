jbanking-benchmarks allows us to track the evolution of jbanking performances from version to version. To achieve that
goal jbanking-benchmarks is using [Java Microbenchmark Harness (JMH)](https://github.com/openjdk/jmh) and the
[JDK Flight Recorder profiler](https://github.com/openjdk/jmc) (and
the [JDK Mission Control](https://github.com/openjdk/jmc) to analyze the results).

Here are the benchmarks that are available :

- Iban (creation and validation, with different IBAN sizes),
- Creditor identifiers (creation and validation, with different CI sizes),
- BIC (creation and validation, for BIC8 and BIC11),
- Calendars (check if a date is a business day).

## Run the benchmarks

First install the current SNAPSHOT version of jbanking in you local Maven repository. Then use the [`run`](/run)
script with a JDK 8 in your path: `./run`.

By default, the script run the benchmarks against the latest of each major version and produces, in a temporary
directory (e.g. `/tmp/jbanking-benchmarks.85iJgvVVOc`), a file named `jbanking-benchmarks.md`. This files contains the
aggregated results of all the benchmarks processed, sorted and formatted as a Markdown table.

The JFR files for each version and each test can be found in subdirectories
(e.g. `2.1.0/b.BicBenchmark.isValid-Throughput-value-AECFFR21/profile.jfr`) of the temporary directory.

Options are available to target only specific versions or benchmarks. See `./run --help` for more information.

## Latest results

All test were run with in the following conditions :

- [AMD Ryzen 7 4800H](https://www.amd.com/fr/products/apu/amd-ryzen-7-4800h),
- JDK 1.8.0_332, OpenJDK 64-Bit Server VM ([Eclipse Temurin build](https://adoptium.net/temurin/releases/)),
- 128 MB of heap memory,
- 3 warmup iterations,
- 3 iterations,
- 10 seconds per iteration,
- 1 thread,
- blackhole mode: full + dont-inline hint.

### BIC

| Name                                     | Parameter                       | Version        | Ops/s      | Increase |
|------------------------------------------|---------------------------------|----------------|------------|----------|
| b.BicBenchmark.creation                  | AECFFR21                        | 2.1.0          | 2,829,008  | N/A      |
| b.BicBenchmark.creation                  | AECFFR21                        | 3.4.0          | 7,249,899  | 156.27%  |
| b.BicBenchmark.creation                  | AECFFR21                        | 4.0.0-SNAPSHOT | 22,657,649 | 212.52%  |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 2.1.0          | 2,733,999  | N/A      |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 3.4.0          | 8,223,643  | 200.79%  |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 4.0.0-SNAPSHOT | 25,649,768 | 211.90%  |
| b.BicBenchmark.validation                | AECFFR21                        | 2.1.0          | 3,104,155  | N/A      |
| b.BicBenchmark.validation                | AECFFR21                        | 3.4.0          | 8,931,005  | 187.71%  |
| b.BicBenchmark.validation                | AECFFR21                        | 4.0.0-SNAPSHOT | 30,925,142 | 246.27%  |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 2.1.0          | 2,860,202  | N/A      |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 3.4.0          | 8,955,774  | 213.12%  |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 4.0.0-SNAPSHOT | 27,706,544 | 209.37%  |

### IBAN

| Name                                     | Parameter                       | Version        | Ops/s      | Increase |
|------------------------------------------|---------------------------------|----------------|------------|----------|
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 2.1.0          | 524,002    | N/A      |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 3.4.0          | 1,133,703  | 116.35%  |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 4.0.0-SNAPSHOT | 5,408,809  | 377.09%  |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 2.1.0          | 554,747    | N/A      |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 3.4.0          | 940,360    | 69.51%   |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 4.0.0-SNAPSHOT | 2,978,276  | 216.72%  |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 2.1.0          | 671,508    | N/A      |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 3.4.0          | 1,529,074  | 127.71%  |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 4.0.0-SNAPSHOT | 7,601,079  | 397.10%  |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 2.1.0          | 530,710    | N/A      |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 3.4.0          | 1,208,639  | 127.74%  |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 4.0.0-SNAPSHOT | 5,934,524  | 391.01%  |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 2.1.0          | 555,508    | N/A      |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 3.4.0          | 974,202    | 75.37%   |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 4.0.0-SNAPSHOT | 3,047,429  | 212.81%  |
| b.IbanBenchmark.validation               | NO2451742753161                 | 2.1.0          | 656,613    | N/A      |
| b.IbanBenchmark.validation               | NO2451742753161                 | 3.4.0          | 1,549,316  | 135.96%  |
| b.IbanBenchmark.validation               | NO2451742753161                 | 4.0.0-SNAPSHOT | 7,650,197  | 393.78%  |

### Creditor identifiers

| Name                                     | Parameter                       | Version        | Ops/s      | Increase |
|------------------------------------------|---------------------------------|----------------|------------|----------|
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 2.1.0          | 1,287,131  | N/A      |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 3.4.0          | 1,571,896  | 22.12%   |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 4.0.0-SNAPSHOT | 4,663,745  | 196.70%  |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 2.1.0          | 1,473,941  | N/A      |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 3.4.0          | 2,101,759  | 42.59%   |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 4.0.0-SNAPSHOT | 8,107,983  | 285.77%  |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 2.1.0          | 700,040    | N/A      |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 3.4.0          | 1,386,933  | 98.12%   |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 4.0.0-SNAPSHOT | 3,537,574  | 155.06%  |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 2.1.0          | 1,284,337  | N/A      |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 3.4.0          | 1,547,156  | 20.46%   |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 4.0.0-SNAPSHOT | 4,375,769  | 182.83%  |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 2.1.0          | 1,497,450  | N/A      |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 3.4.0          | 2,194,562  | 46.55%   |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 4.0.0-SNAPSHOT | 8,397,975  | 282.67%  |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 2.1.0          | 686,535    | N/A      |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 3.4.0          | 1,376,117  | 100.44%  |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 4.0.0-SNAPSHOT | 4,266,289  | 210.02%  |

### Calendar

| Name                                     | Parameter                       | Version        | Ops/s      | Increase |
|------------------------------------------|---------------------------------|----------------|------------|----------|
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 3.4.0          | 1,488,633  | N/A      |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 4.0.0-SNAPSHOT | 1,526,671  | 2.56%    |
| b.CalendarBenchmark.validation           | PARIS                           | 3.4.0          | 2,644,530  | N/A      |
| b.CalendarBenchmark.validation           | PARIS                           | 4.0.0-SNAPSHOT | 2,748,954  | 3.95%    |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 3.4.0          | 20,497,110 | N/A      |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 4.0.0-SNAPSHOT | 21,279,043 | 3.81%    |
