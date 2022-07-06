package fr.marcwrobel.jbanking.calendar;

import static java.time.LocalDate.MAX;
import static java.time.LocalDate.MIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CalendarTest {

  private static final LocalDate ODD = LocalDate.of(2020, 1, 11);
  private static final LocalDate EVEN = LocalDate.of(2020, 1, 12);

  private static class OddCalendar implements Calendar {

    @Override
    public boolean isHoliday(LocalDate date) {
      return date.getDayOfMonth() % 2 != 0;
    }

    @Override
    public Set<Holiday> getHolidaysFor(LocalDate date) {
      throw new UnsupportedOperationException();
    }
  }

  @Test
  void businessDay() {
    Calendar calendar = new OddCalendar();
    assertTrue(calendar.isHoliday(ODD));
    assertFalse(calendar.isBusinessDay(ODD));
    assertFalse(calendar.isHoliday(EVEN));
    assertTrue(calendar.isBusinessDay(EVEN));
  }

  @Test
  void shift() {
    Calendar calendar = new OddCalendar();

    assertEquals(ODD, calendar.shift(ODD, 0));
    assertEquals(EVEN, calendar.shift(EVEN, 0));

    assertEquals(ODD.plusDays(1), calendar.shift(ODD, 1));
    assertEquals(EVEN.plusDays(2), calendar.shift(EVEN, 1));

    assertEquals(ODD.minusDays(1), calendar.shift(ODD, -1));
    assertEquals(EVEN.minusDays(2), calendar.shift(EVEN, -1));
  }

  @Test
  void nextOrSame() {
    Calendar calendar = new OddCalendar();
    assertEquals(ODD.plusDays(1), calendar.nextOrSame(ODD));
    assertEquals(EVEN, calendar.nextOrSame(EVEN));
  }

  @Test
  void previousOrSame() {
    Calendar calendar = new OddCalendar();
    assertEquals(ODD.minusDays(1), calendar.previousOrSame(ODD));
    assertEquals(EVEN, calendar.previousOrSame(EVEN));
  }

  @Test
  void next() {
    Calendar calendar = new OddCalendar();
    assertEquals(ODD.plusDays(1), calendar.next(ODD));
    assertEquals(EVEN.plusDays(2), calendar.next(EVEN));
  }

  @Test
  void previous() {
    Calendar calendar = new OddCalendar();
    assertEquals(ODD.minusDays(1), calendar.previous(ODD));
    assertEquals(EVEN.minusDays(2), calendar.previous(EVEN));
  }

  @Test
  void holidaysWithinParameterValidation() {
    Calendar calendar = new OddCalendar();
    assertThrows(IllegalArgumentException.class, () -> calendar.holidaysWithin(MAX, MIN));
  }

  @Test
  void holidaysWithin() {
    Calendar calendar = new OddCalendar();
    assertEquals(Arrays.asList(ODD.minusDays(2), ODD, ODD.plusDays(2)),
        calendar.holidaysWithin(ODD.minusDays(2), ODD.plusDays(2)));
  }

  @Test
  void businessDaysWithinParameterValidation() {
    Calendar calendar = new OddCalendar();
    assertThrows(IllegalArgumentException.class, () -> calendar.businessDaysWithin(MAX, MIN));
  }

  @Test
  void businessDaysWithin() {
    Calendar calendar = new OddCalendar();
    assertEquals(Arrays.asList(ODD.minusDays(1), ODD.plusDays(1)),
        calendar.businessDaysWithin(ODD.minusDays(2), ODD.plusDays(2)));
  }
}
