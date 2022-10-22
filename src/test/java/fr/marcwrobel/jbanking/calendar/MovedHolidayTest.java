package fr.marcwrobel.jbanking.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import org.junit.jupiter.api.Test;

class MovedHolidayTest {

  private static final int YEAR = 2020;
  private static final Month MONTH = Month.JUNE;
  private static final int DAY = 15;
  private static final LocalDate FROM = LocalDate.of(YEAR, MONTH, DAY);
  private static final LocalDate TO = LocalDate.of(YEAR, MONTH, DAY + 1);

  private static final Holiday BASE = new MonthDayHoliday(MonthDay.of(MONTH, DAY));
  private static final Holiday HOLIDAY = new MovedHoliday(BASE, FROM, TO);

  @Test
  void baseCannotBeNull() {
    assertThrows(NullPointerException.class, () -> new RelativeHoliday(null, 0));
  }

  @Test
  void holidayIsMoved() {
    assertTrue(HOLIDAY.check(FROM.minusYears(1)));
    assertFalse(HOLIDAY.check(FROM));
    assertTrue(HOLIDAY.check(TO));
    assertTrue(HOLIDAY.check(FROM.plusYears(1)));
  }

  @Test
  void equalsAndHashCodeAndToString() {
    Holiday holiday1 = HOLIDAY;
    Holiday holiday2 = HOLIDAY;
    Holiday holiday3 = new MovedHoliday(new MonthDayHoliday(MonthDay.of(MONTH, DAY + 3)));
    Holiday holiday4 = new MovedHoliday(BASE);

    assertEquals(holiday1, holiday2);
    assertEquals(holiday2, holiday1);
    assertEquals(holiday1, holiday1);
    assertEquals(holiday1.hashCode(), holiday2.hashCode());
    assertEquals(holiday1.toString(), holiday2.toString());

    assertNotEquals(holiday1, holiday3);
    assertNotEquals(holiday3, holiday1);
    assertNotEquals(holiday1.hashCode(), holiday3.hashCode());
    assertNotEquals(holiday1.toString(), holiday3.toString());

    assertNotEquals(holiday1, holiday4);
    assertNotEquals(holiday4, holiday1);
    assertNotEquals(holiday1.hashCode(), holiday4.hashCode());
    assertNotEquals(holiday1.toString(), holiday4.toString());

    assertNotEquals(null, holiday1);
    assertNotEquals(new Object(), holiday1);
  }
}
