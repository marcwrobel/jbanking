package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static java.time.Month.JUNE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SuppressedHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = JUNE;
  private static final int DAY = 15;

  private static final MonthDayHoliday BASE = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
  private static final SuppressedHoliday HOLIDAY = new SuppressedHoliday(BASE, YEAR);

  @Test
  void baseCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new SuppressedHoliday(null, YEAR));
  }

  @Test
  void suppressedYearsCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> new SuppressedHoliday(BASE, (Collection<Integer>) null));
  }

  @Test
  void suppressedYearsCannotBeNull2() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new SuppressedHoliday(BASE, (Integer[]) null));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> HOLIDAY.check(null));
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 100, 1970, 2050, 10000 })
  void holidayCheckSucceed(int year) {
    assertThat(HOLIDAY.check(LocalDate.of(year, MONTH, DAY))).isTrue();
  }

  @Test
  void holidayIsSuppressed() {
    assertThat(HOLIDAY.check(LocalDate.of(YEAR, MONTH, DAY))).isFalse();
  }

  @Test
  void equalsAndHashCodeAndToString() {
    testEquality(new SuppressedHoliday(new MonthDayHoliday(MonthDay.of(MONTH, DAY)), YEAR),
        new SuppressedHoliday(new MonthDayHoliday(MonthDay.of(MONTH, DAY)), YEAR));
  }
}
