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

First install the current SNAPSHOT version of jbanking in you local Maven repository. Then use the [`run`](/run) script
with the targeted versions:

```shell
./run
```

The script run the benchmarks against the latest of each major version and produces, in a temporary directory (e.g.
`/tmp/jbanking-benchmarks.85iJgvVVOc`), two files :

- `jbanking-benchmarks.json`, with the raw results for all versions,
- `jbanking-benchmarks.md`, with the same data as `jbanking-benchmarks.json`, but sorted and formatted to a Markdown
  table.

The JFR files for each version and each test can be found in subdirectories
(e.g. `2.1.0/b.BicBenchmark.isValid-Throughput-value-AECFFR21/profile.jfr`).

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

| name                                     | parameter                       | throughput (ops/s) | version        |
|------------------------------------------|---------------------------------|--------------------|----------------|
| b.BicBenchmark.creation                  | AECFFR21                        | 2458425.3893140806 | 1.0            |
| b.BicBenchmark.creation                  | AECFFR21                        | 2530410.4957453604 | 2.1.0          |
| b.BicBenchmark.creation                  | AECFFR21                        | 6854009.90896896   | 3.4.0          |
| b.BicBenchmark.creation                  | AECFFR21                        | 7190349.453608278  | 4.0.0-SNAPSHOT |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 2473169.8704783493 | 1.0            |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 2500515.832375454  | 2.1.0          |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 5890216.802319315  | 4.0.0-SNAPSHOT |
| b.BicBenchmark.creation                  | AECFFR21XXX                     | 7718496.250356044  | 3.4.0          |
| b.BicBenchmark.validation                | AECFFR21                        | 2688566.1794009567 | 1.0            |
| b.BicBenchmark.validation                | AECFFR21                        | 2689057.7059752233 | 2.1.0          |
| b.BicBenchmark.validation                | AECFFR21                        | 9101923.709667956  | 3.4.0          |
| b.BicBenchmark.validation                | AECFFR21                        | 9135348.456472605  | 4.0.0-SNAPSHOT |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 2534206.1831777333 | 1.0            |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 2572414.9971009106 | 2.1.0          |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 7063151.569808416  | 4.0.0-SNAPSHOT |
| b.BicBenchmark.validation                | AECFFR21XXX                     | 7622818.14566424   | 3.4.0          |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 1504555.5300917418 | 3.4.0          |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 1534339.7479736202 | 4.0.0-SNAPSHOT |
| b.CalendarBenchmark.validation           | NEW_YORK_FED                    | 1692413.5557553722 | 2.1.0          |
| b.CalendarBenchmark.validation           | PARIS                           | 2675125.3337022555 | 4.0.0-SNAPSHOT |
| b.CalendarBenchmark.validation           | PARIS                           | 2728133.4505634275 | 2.1.0          |
| b.CalendarBenchmark.validation           | PARIS                           | 2763490.475512153  | 3.4.0          |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 19962832.700334243 | 2.1.0          |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 20714400.236055505 | 4.0.0-SNAPSHOT |
| b.CalendarBenchmark.validation           | SATURDAY_SUNDAY                 | 21136042.62783764  | 3.4.0          |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 1460316.290385738  | 4.0.0-SNAPSHOT |
| b.CreditorIdentifierBenchmark.creation   | BE69ZZZ050D000000008            | 1497765.9790729887 | 3.4.0          |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 1889137.5787130364 | 4.0.0-SNAPSHOT |
| b.CreditorIdentifierBenchmark.creation   | CY54ZZZ003A                     | 1979223.0826955668 | 3.4.0          |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 1225656.425420342  | 3.4.0          |
| b.CreditorIdentifierBenchmark.creation   | GB23ZZZSDDBARC000000ABCD1234    | 1278560.7573129258 | 4.0.0-SNAPSHOT |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 1394813.4451051683 | 3.4.0          |
| b.CreditorIdentifierBenchmark.validation | BE69ZZZ050D000000008            | 1401998.860281951  | 4.0.0-SNAPSHOT |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 1946655.844030205  | 4.0.0-SNAPSHOT |
| b.CreditorIdentifierBenchmark.validation | CY54ZZZ003A                     | 2032682.6850879835 | 3.4.0          |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 1255077.6876865244 | 4.0.0-SNAPSHOT |
| b.CreditorIdentifierBenchmark.validation | GB23ZZZSDDBARC000000ABCD1234    | 1277956.7236680975 | 3.4.0          |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 496015.7954783158  | 2.1.0          |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 512315.2743234933  | 1.0            |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 1088855.9919270808 | 3.4.0          |
| b.IbanBenchmark.creation                 | ES2837832292261368335005        | 1183039.8313794248 | 4.0.0-SNAPSHOT |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 521457.4869068836  | 2.1.0          |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 540858.9902576734  | 1.0            |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 902359.7751483739  | 3.4.0          |
| b.IbanBenchmark.creation                 | MT84AIWA00813109843252965695890 | 903372.8744631289  | 4.0.0-SNAPSHOT |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 641355.2057275715  | 2.1.0          |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 650302.3766157505  | 1.0            |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 1459558.6377279593 | 3.4.0          |
| b.IbanBenchmark.creation                 | NO2451742753161                 | 1493278.4903499328 | 4.0.0-SNAPSHOT |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 503625.82105215156 | 2.1.0          |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 540863.0679029879  | 1.0            |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 1139805.7382518833 | 4.0.0-SNAPSHOT |
| b.IbanBenchmark.validation               | ES2837832292261368335005        | 1145318.412868684  | 3.4.0          |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 532389.3457544685  | 2.1.0          |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 545268.5773725305  | 1.0            |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 898952.3281776108  | 3.4.0          |
| b.IbanBenchmark.validation               | MT84AIWA00813109843252965695890 | 983117.6554380748  | 4.0.0-SNAPSHOT |
| b.IbanBenchmark.validation               | NO2451742753161                 | 628418.7206489729  | 2.1.0          |
| b.IbanBenchmark.validation               | NO2451742753161                 | 677235.2470151491  | 1.0            |
| b.IbanBenchmark.validation               | NO2451742753161                 | 1360595.1393715353 | 4.0.0-SNAPSHOT |
| b.IbanBenchmark.validation               | NO2451742753161                 | 1366976.878310357  | 3.4.0          |
