package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.SATURDAY_SUNDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.EnumSet;
import org.junit.jupiter.api.Test;

class SaturdaySundayCalendarTest {

  private static final LocalDate NOW = LocalDate.now();
  private static final LocalDate FROM = LocalDate.of(NOW.getYear() - 10, 1, 1);
  private static final LocalDate TO = LocalDate.of(NOW.getYear() + 10, 12, 31);

  @Test
  void onlySaturdayAndSundayAreBusinessDays() {
    LocalDate current = FROM;

    while (!current.equals(TO)) {
      if (EnumSet.of(SATURDAY, SUNDAY).contains(current.getDayOfWeek())) {
        assertThat(SATURDAY_SUNDAY.isBusinessDay(current)).isFalse();
        assertThat(SATURDAY_SUNDAY.isHoliday(current)).isTrue();
      } else {
        assertThat(SATURDAY_SUNDAY.isBusinessDay(current)).isTrue();
        assertThat(SATURDAY_SUNDAY.isHoliday(current)).isFalse();
      }

      current = current.plusDays(1);
    }
  }
}
