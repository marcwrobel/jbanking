package b;

import fr.marcwrobel.jbanking.iban.Iban;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class IbanBenchmark {

  @Param({"NO2451742753161", "ES2837832292261368335005", "MT84AIWA00813109843252965695890"})
  public String value;

  @Benchmark
  public Iban creation() {
    return new Iban(value);
  }

  @Benchmark
  public boolean validation() {
    return Iban.isValid(value);
  }
}
