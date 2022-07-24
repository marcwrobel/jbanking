package b;

import fr.marcwrobel.jbanking.bic.Bic;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class BicBenchmark {

  @Param({ "AECFFR21XXX", "AECFFR21" })
  public String value;

  @Benchmark
  public Bic creation() {
    return new Bic(value);
  }

  @Benchmark
  public boolean validation() {
    return Bic.isValid(value);
  }
}
