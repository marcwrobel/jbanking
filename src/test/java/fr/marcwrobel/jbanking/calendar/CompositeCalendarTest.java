package fr.marcwrobel.jbanking.calendar;

import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.NEW_YORK_FED;
import static fr.marcwrobel.jbanking.calendar.FinancialCalendars.PARIS;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class CompositeCalendarTest {

  @Test
  void cannotInstantiateWithoutCalendars() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new CompositeCalendar((Collection<Calendar>) null));

    List<Calendar> emptyList = emptyList();
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new CompositeCalendar(emptyList));
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(CompositeCalendar::new);
  }

  @Test
  void holidaysInBothCalendarsAreRecognized() {
    CompositeCalendar calendar = new CompositeCalendar(PARIS, NEW_YORK_FED);
    assertThat(calendar.isHoliday(LocalDate.of(2020, 1, 1))).isTrue(); // in both
  }

  @Test
  void holidaysInOneCalendarAreRecognized() {
    CompositeCalendar calendar = new CompositeCalendar(PARIS, NEW_YORK_FED);

    LocalDate fourthOfJuly = LocalDate.of(2017, 7, 4); // NEW_YORK_FED
    assertThat(calendar.isHoliday(fourthOfJuly)).isTrue();
    assertThat(calendar.getHolidaysFor(fourthOfJuly)).hasSize(1);

    LocalDate bastilleDay = LocalDate.of(2017, 7, 14); // PARIS
    assertThat(calendar.isHoliday(bastilleDay)).isTrue();
    assertThat(calendar.getHolidaysFor(bastilleDay)).hasSize(1);
  }

  @Test
  void businessDayInBothCalendarsAreRecognized() {
    CompositeCalendar calendar = new CompositeCalendar(PARIS, NEW_YORK_FED);
    assertThat(calendar.isBusinessDay(LocalDate.of(2020, 7, 1))).isTrue();
  }
}
