package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.internal.TestUtils.testEquality;
import static java.time.Month.JUNE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;

class FixedHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = JUNE;
  private static final int DAY = 15;

  private static final FixedHoliday HOLIDAY = new FixedHoliday(LocalDate.of(YEAR, MONTH, DAY));

  @Test
  void datesCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new MonthDayHoliday(null));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> HOLIDAY.check(null));
  }

  @Test
  void checkSucceedForTheGivenDate() {
    assertThat(HOLIDAY.check(LocalDate.of(YEAR, MONTH, DAY))).isTrue();
  }

  @Test
  void checkFailsForOtherDates() {
    assertThat(HOLIDAY.check(LocalDate.of(YEAR, MONTH, DAY + 1))).isFalse();
    assertThat(HOLIDAY.check(LocalDate.of(YEAR, MONTH, DAY - 1))).isFalse();
  }

  @Test
  void equalsAndHashCodeAndToString() {
    testEquality(new FixedHoliday(LocalDate.of(YEAR, MONTH, DAY)),
        new FixedHoliday(LocalDate.of(YEAR, MONTH, DAY)));
  }
}
