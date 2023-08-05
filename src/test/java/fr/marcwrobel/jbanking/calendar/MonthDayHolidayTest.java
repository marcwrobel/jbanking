package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static java.time.Month.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MonthDayHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = JUNE;
  private static final int DAY = 15;

  private static final MonthDayHoliday HOLIDAY = new MonthDayHoliday(MonthDay.of(MONTH, DAY));

  @Test
  void monthDayCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new MonthDayHoliday(null));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 100, 1970, YEAR, 2050, 10000 })
  void holidayCheckSucceed(int year) {
    assertThat(HOLIDAY.check(LocalDate.of(year, MONTH, DAY))).isTrue();
  }

  @Test
  void onlyOneHolidayPerYear() {
    LocalDate start = LocalDate.of(YEAR, JANUARY, 1);
    LocalDate end = LocalDate.of(start.getYear(), DECEMBER, 31);

    int count = 0;
    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
      if (HOLIDAY.check(date)) {
        count++;
      }
    }

    assertThat(count).isEqualTo(1);
  }

  @Test
  void equalsAndHashCodeAndToString() {
    testEquality(new MonthDayHoliday(MonthDay.of(MONTH, DAY)),
        new MonthDayHoliday(MonthDay.of(MONTH, DAY)));
  }
}
