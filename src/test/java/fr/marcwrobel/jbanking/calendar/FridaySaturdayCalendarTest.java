package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.FRIDAY_SATURDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.EnumSet;
import org.junit.jupiter.api.Test;

class FridaySaturdayCalendarTest {

  private static final LocalDate NOW = LocalDate.now();
  private static final LocalDate FROM = LocalDate.of(NOW.getYear() - 10, 1, 1);
  private static final LocalDate TO = LocalDate.of(NOW.getYear() + 10, 12, 31);

  @Test
  void onlySaturdayAndSundayAreBusinessDays() {
    LocalDate current = FROM;

    while (!current.equals(TO)) {
      if (EnumSet.of(FRIDAY, SATURDAY).contains(current.getDayOfWeek())) {
        assertThat(FRIDAY_SATURDAY.isBusinessDay(current)).isFalse();
        assertThat(FRIDAY_SATURDAY.isHoliday(current)).isTrue();
      } else {
        assertThat(FRIDAY_SATURDAY.isBusinessDay(current)).isTrue();
        assertThat(FRIDAY_SATURDAY.isHoliday(current)).isFalse();
      }

      current = current.plusDays(1);
    }
  }
}
