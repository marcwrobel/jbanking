package fr.marcwrobel.jbanking.calendar;

import static java.time.Month.JUNE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    assertThrows(NullPointerException.class, () -> new MonthDayHoliday(null));
  }

  @Test
  void checkDoesNotAcceptNull() {
    assertThrows(NullPointerException.class, () -> HOLIDAY.check(null));
  }

  @Test
  void checkSucceedForTheGivenDate() {
    assertTrue(HOLIDAY.check(LocalDate.of(YEAR, MONTH, DAY)));
  }

  @Test
  void checkFailsForOtherDates() {
    assertFalse(HOLIDAY.check(LocalDate.of(YEAR, MONTH, DAY + 1)));
    assertFalse(HOLIDAY.check(LocalDate.of(YEAR, MONTH, DAY - 1)));
  }

  @Test
  void equalsAndHashCodeAndToString() {
    Holiday holiday1 = new FixedHoliday(LocalDate.of(YEAR, MONTH, DAY));
    Holiday holiday2 = new FixedHoliday(LocalDate.of(YEAR, MONTH, DAY));

    assertEquals(holiday1, holiday2);
    assertEquals(holiday2, holiday1);
    assertEquals(holiday1, holiday1);
    assertEquals(holiday1.hashCode(), holiday2.hashCode());
    assertEquals(holiday1.toString(), holiday2.toString());

    assertNotEquals(null, holiday1);
    assertNotEquals(new Object(), holiday1);
  }
}
