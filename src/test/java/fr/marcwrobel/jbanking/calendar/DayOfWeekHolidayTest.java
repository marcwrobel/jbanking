package fr.marcwrobel.jbanking.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DayOfWeekHolidayTest {

  private static final int YEAR = 2020;
  private static final int MONTH = 6;
  private static final int DAY = 16;

  private static final LocalDate CURRENT_WEEK = LocalDate.of(YEAR, MONTH, DAY);

  private static final DayOfWeekHoliday HOLIDAY = DayOfWeekHoliday.TUESDAY;

  @Test
  void checkDoesNotAcceptNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> HOLIDAY.check(null));
  }

  @Test
  void holidayCheckSucceed() {
    assertThat(HOLIDAY.check(CURRENT_WEEK)).isTrue();
  }

  @Test
  void onlyOneHolidayPerWeek() {
    LocalDate start = LocalDate.of(YEAR, MONTH, DAY);
    LocalDate end = start.with(DayOfWeek.SUNDAY);

    int count = 0;
    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
      if (HOLIDAY.check(date)) {
        count++;
      }
    }

    assertThat(count).isEqualTo(1);
  }
}
