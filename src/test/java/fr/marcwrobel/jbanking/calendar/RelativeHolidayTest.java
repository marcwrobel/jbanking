package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RelativeHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = Month.JUNE;
  private static final int DAY = 15;
  private static final int SHIFT = 1;

  private static final Holiday BASE = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
  private static final RelativeHoliday HOLIDAY = new RelativeHoliday(BASE, SHIFT);

  @Test
  void baseCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new RelativeHoliday(null, 0));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 100, 1970, YEAR, 2050, 10000 })
  void holidayCheckSucceed(int year) {
    assertThat(HOLIDAY.check(LocalDate.of(year, MONTH, DAY).plusDays(SHIFT))).isTrue();
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
    testEquality(new RelativeHoliday(BASE, SHIFT), new RelativeHoliday(BASE, SHIFT));
  }
}
