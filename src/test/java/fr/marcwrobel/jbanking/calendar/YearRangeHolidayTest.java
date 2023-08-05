package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ValueRange;
import org.junit.jupiter.api.Test;

class YearRangeHolidayTest {

  private static final int YEAR = 2020;
  private static final ValueRange RANGE = ValueRange.of(YEAR - 1, YEAR + 1);
  private static final Month MONTH = Month.JUNE;
  private static final int DAY = 15;
  private static final LocalDate DATE = LocalDate.of(YEAR, MONTH, DAY);

  private static final Holiday BASE = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
  private static final Holiday HOLIDAY = new YearRangeHoliday(BASE, RANGE);

  @Test
  void baseCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new YearRangeHoliday(null, RANGE));
  }

  @Test
  void holidayIsPunctual() {
    assertThat(HOLIDAY.check(DATE.minusYears(2))).isFalse();
    assertThat(HOLIDAY.check(DATE.minusYears(1))).isTrue();
    assertThat(HOLIDAY.check(DATE)).isTrue();
    assertThat(HOLIDAY.check(DATE.plusYears(1))).isTrue();
    assertThat(HOLIDAY.check(DATE.plusYears(2))).isFalse();
  }

  @Test
  void equalsAndHashCodeAndToString() {
    testEquality(new YearRangeHoliday(BASE, RANGE), new YearRangeHoliday(BASE, RANGE));
  }
}
