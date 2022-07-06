package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.NEW_YORK_FED;
import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.PARIS;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class CompositeCalendarTest {

  @Test
  void cannotInstantiateWithoutCalendars() {
    // noinspection ConstantConditions
    assertThrows(NullPointerException.class, () -> new CompositeCalendar((Collection<Calendar>) null));

    List<Calendar> emptyList = emptyList();
    assertThrows(IllegalArgumentException.class, () -> new CompositeCalendar(emptyList));
    assertThrows(IllegalArgumentException.class, CompositeCalendar::new);
  }

  @Test
  void holidaysInBothCalendarsAreRecognized() {
    CompositeCalendar calendar = new CompositeCalendar(PARIS, NEW_YORK_FED);
    assertTrue(calendar.isHoliday(LocalDate.of(2020, 1, 1))); // in both
  }

  @Test
  void holidaysInOneCalendarAreRecognized() {
    CompositeCalendar calendar = new CompositeCalendar(PARIS, NEW_YORK_FED);

    LocalDate fourthOfJuly = LocalDate.of(2017, 7, 4); // NEW_YORK_FED
    assertTrue(calendar.isHoliday(fourthOfJuly));
    assertEquals(1, calendar.getHolidaysFor(fourthOfJuly).size());

    LocalDate bastilleDay = LocalDate.of(2017, 7, 14); // PARIS
    assertTrue(calendar.isHoliday(bastilleDay));
    assertEquals(1, calendar.getHolidaysFor(bastilleDay).size());
  }

  @Test
  void businessDayInBothCalendarsAreRecognized() {
    CompositeCalendar calendar = new CompositeCalendar(PARIS, NEW_YORK_FED);
    assertTrue(calendar.isBusinessDay(LocalDate.of(2020, 7, 1)));
  }
}
