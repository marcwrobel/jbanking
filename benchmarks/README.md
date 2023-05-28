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

First install the current SNAPSHOT version of jbanking in you local Maven repository. Then use the [`run`](run)
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
- JDK 1.8.0_372, OpenJDK 64-Bit Server VM ([Eclipse Temurin build](https://adoptium.net/temurin/releases/)),
- 128 MB of heap memory,
- 3 warmup iterations,
- 3 iterations,
- 10 seconds per iteration,
- 1 thread,
- blackhole mode: full + dont-inline hint.

The results presented here are only valid for the hardware and the software used to run them, and
enable us to roughly compare the performances of jbanking over versions. Small (< 10%) increases or
decreases must be ignored.

### BIC

| Name                      | Parameter   | Version | Ops/s      | Increase |
|---------------------------|-------------|---------|------------|----------|
| b.BicBenchmark.creation   | AECFFR21    | 2.1.0   | 2,743,335  | N/A      |
| b.BicBenchmark.creation   | AECFFR21    | 3.4.0   | 7,336,205  | 167.42%  |
| b.BicBenchmark.creation   | AECFFR21    | 4.0.0   | 22,495,144 | 206.63%  |
| b.BicBenchmark.creation   | AECFFR21XXX | 2.1.0   | 2,684,730  | N/A      |
| b.BicBenchmark.creation   | AECFFR21XXX | 3.4.0   | 7,183,931  | 167.58%  |
| b.BicBenchmark.creation   | AECFFR21XXX | 4.0.0   | 23,673,678 | 229.54%  |
| b.BicBenchmark.validation | AECFFR21    | 2.1.0   | 2,839,685  | N/A      |
| b.BicBenchmark.validation | AECFFR21    | 3.4.0   | 9,126,799  | 221.40%  |
| b.BicBenchmark.validation | AECFFR21    | 4.0.0   | 30,332,360 | 232.34%  |
| b.BicBenchmark.validation | AECFFR21XXX | 2.1.0   | 2,942,164  | N/A      |
| b.BicBenchmark.validation | AECFFR21XXX | 3.4.0   | 9,179,068  | 211.98%  |
| b.BicBenchmark.validation | AECFFR21XXX | 4.0.0   | 27,337,955 | 197.83%  |


### IBAN

| Name                       | Parameter                       | Version | Ops/s     | Increase |
|----------------------------|---------------------------------|---------|-----------|----------|
| b.IbanBenchmark.creation   | ES2837832292261368335005        | 2.1.0   | 525,758   | N/A      |
| b.IbanBenchmark.creation   | ES2837832292261368335005        | 3.4.0   | 1,196,611 | 127.60%  |
| b.IbanBenchmark.creation   | ES2837832292261368335005        | 4.0.0   | 4,988,199 | 316.86%  |
| b.IbanBenchmark.creation   | MT84AIWA00813109843252965695890 | 2.1.0   | 537,777   | N/A      |
| b.IbanBenchmark.creation   | MT84AIWA00813109843252965695890 | 3.4.0   | 937,856   | 74.39%   |
| b.IbanBenchmark.creation   | MT84AIWA00813109843252965695890 | 4.0.0   | 2,822,266 | 200.93%  |
| b.IbanBenchmark.creation   | NO2451742753161                 | 2.1.0   | 665,184   | N/A      |
| b.IbanBenchmark.creation   | NO2451742753161                 | 3.4.0   | 1,503,839 | 126.08%  |
| b.IbanBenchmark.creation   | NO2451742753161                 | 4.0.0   | 7,132,829 | 374.31%  |
| b.IbanBenchmark.validation | ES2837832292261368335005        | 2.1.0   | 532,961   | N/A      |
| b.IbanBenchmark.validation | ES2837832292261368335005        | 3.4.0   | 1,168,217 | 119.19%  |
| b.IbanBenchmark.validation | ES2837832292261368335005        | 4.0.0   | 5,341,337 | 357.22%  |
| b.IbanBenchmark.validation | MT84AIWA00813109843252965695890 | 2.1.0   | 534,997   | N/A      |
| b.IbanBenchmark.validation | MT84AIWA00813109843252965695890 | 3.4.0   | 934,482   | 74.67%   |
| b.IbanBenchmark.validation | MT84AIWA00813109843252965695890 | 4.0.0   | 2,993,041 | 220.29%  |
| b.IbanBenchmark.validation | NO2451742753161                 | 2.1.0   | 676,793   | N/A      |
| b.IbanBenchmark.validation | NO2451742753161                 | 3.4.0   | 1,458,368 | 115.48%  |
| b.IbanBenchmark.validation | NO2451742753161                 | 4.0.0   | 8,158,591 | 459.43%  |

### Creditor identifiers

| Name                                     | Parameter                    | Version | Ops/s     | Increase |
|------------------------------------------|------------------------------|---------|-----------|----------|
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008         | 2.1.0   | 1,261,666 | N/A      |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008         | 3.4.0   | 1,515,320 | 20.10%   |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008         | 4.0.0   | 4,285,990 | 182.84%  |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                  | 2.1.0   | 1,398,624 | N/A      |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                  | 3.4.0   | 2,094,990 | 49.79%   |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                  | 4.0.0   | 7,955,744 | 279.75%  |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234 | 2.1.0   | 637,440   | N/A      |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234 | 3.4.0   | 1,295,687 | 103.26%  |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234 | 4.0.0   | 3,511,540 | 171.02%  |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008         | 2.1.0   | 1,272,654 | N/A      |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008         | 3.4.0   | 1,515,902 | 19.11%   |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008         | 4.0.0   | 4,578,655 | 202.04%  |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                  | 2.1.0   | 1,479,744 | N/A      |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                  | 3.4.0   | 2,121,245 | 43.35%   |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                  | 4.0.0   | 7,890,800 | 271.99%  |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234 | 2.1.0   | 665,218   | N/A      |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234 | 3.4.0   | 1,341,645 | 101.68%  |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234 | 4.0.0   | 3,712,698 | 176.73%  |



### Calendar

| Name                           | Parameter       | Version | Ops/s      | Increase |
|--------------------------------|-----------------|---------|------------|----------|
| b.CalendarBenchmark.validation | NEW_YORK_FED    | 4.0.0   | 1,536,760  | N/A      |
| b.CalendarBenchmark.validation | NEW_YORK_FED    | 3.4.0   | 1,565,606  | 1.88%    |
| b.CalendarBenchmark.validation | PARIS           | 4.0.0   | 2,616,921  | N/A      |
| b.CalendarBenchmark.validation | PARIS           | 3.4.0   | 2,665,889  | 1.87%    |
| b.CalendarBenchmark.validation | SATURDAY_SUNDAY | 4.0.0   | 21,520,789 | N/A      |
| b.CalendarBenchmark.validation | SATURDAY_SUNDAY | 3.4.0   | 21,596,440 | 0.35%    |
