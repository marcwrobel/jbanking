package fr.marcwrobel.jbanking.calendar;

import static org.assertj.core.api.Assertions.assertThat;

import com.opengamma.strata.basics.date.HolidayCalendar;
import com.opengamma.strata.basics.date.HolidayCalendars;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

abstract class CalendarTestSupport {

  private final Calendar calendar;

  protected CalendarTestSupport(Calendar calendar) {
    this.calendar = calendar;
  }

  protected void checkWithStrata(String calendarId, LocalDate... excludedDates) {
    HolidayCalendar strataCalendar = HolidayCalendars.of(calendarId);
    LocalDate date = LocalDate.of(2000, 1, 1);
    LocalDate to = LocalDate.of(2099, 12, 31);
    Set<LocalDate> excluded = new HashSet<>(Arrays.asList(excludedDates));

    while (!date.equals(to)) {
      if (!excluded.contains(date)) {
        boolean isHoliday = calendar.isHoliday(date);
        boolean isStrataHoliday = strataCalendar.isHoliday(date);
        Set<Holiday> holidays = calendar.getHolidaysFor(date);
        assertThat(isHoliday).as(String.format("%s : strata=%s, jbanking=%s (%s)", date, isStrataHoliday, isHoliday, holidays))
            .isEqualTo(isStrataHoliday);
      }

      date = date.plusDays(1);
    }
  }

  private List<LocalDate> filterOutWeekends(List<LocalDate> dates) {
    return dates.stream()
        .filter(d -> d.getDayOfWeek() != DayOfWeek.SATURDAY && d.getDayOfWeek() != DayOfWeek.SUNDAY)
        .collect(Collectors.toList());
  }
}
