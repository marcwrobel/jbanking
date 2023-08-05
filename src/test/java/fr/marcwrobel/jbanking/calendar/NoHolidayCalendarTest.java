package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.NO_HOLIDAYS;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class NoHolidayCalendarTest {

  private static final LocalDate NOW = LocalDate.now();
  private static final LocalDate FROM = LocalDate.of(NOW.getYear() - 10, 1, 1);
  private static final LocalDate TO = LocalDate.of(NOW.getYear() + 10, 12, 31);

  @Test
  void everyPastAndFutureDaysAreBusinessDays() {
    LocalDate current = FROM;

    while (!current.equals(TO)) {
      assertThat(NO_HOLIDAYS.isBusinessDay(current)).isTrue();
      assertThat(NO_HOLIDAYS.isHoliday(current)).isFalse();
      assertThat(NO_HOLIDAYS.getHolidaysFor(current)).isEmpty();
      current = current.plusDays(1);
    }

    assertThat(NO_HOLIDAYS.holidaysWithin(FROM, TO)).isEmpty();
    assertThat(NO_HOLIDAYS.businessDaysWithin(FROM, TO)).isNotEmpty();
  }

  @Test
  void previousAndNextDaysAreBusinessDays() {
    assertThat(NO_HOLIDAYS.previous(NOW)).isEqualTo(NOW.minusDays(1));
    assertThat(NO_HOLIDAYS.next(NOW)).isEqualTo(NOW.plusDays(1));
  }
}
