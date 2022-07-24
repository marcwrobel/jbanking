package b;

import fr.marcwrobel.jbanking.creditor.CreditorIdentifier;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class CreditorIdentifierBenchmark {

  @Param({"CY54ZZZ003A", "BE69ZZZ050D000000008", "GB23ZZZSDDBARC000000ABCD1234"})
  public String value;

  @Benchmark
  public CreditorIdentifier creation() {
    return new CreditorIdentifier(value);
  }

  @Benchmark
  public boolean validation() {
    return CreditorIdentifier.isValid(value);
  }
}
