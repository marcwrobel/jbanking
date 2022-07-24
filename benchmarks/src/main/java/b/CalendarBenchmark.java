package b;

import fr.marcwrobel.jbanking.calendar.FinancialCalendars;
import java.time.LocalDate;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class CalendarBenchmark {

  private static final LocalDate DATE = LocalDate.of(2022, 7, 25);

  @Param({"SATURDAY_SUNDAY", "PARIS", "NEW_YORK_FED"})
  public String value;

  @Benchmark
  public boolean validation() {
    return FinancialCalendars.valueOf(value).isHoliday(DATE);
  }
}
