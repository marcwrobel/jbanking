package fr.marcwrobel.jbanking.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Holidays shifting strategies.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public enum ShiftingStrategy {

  /**
   * For {@link Holiday}s that are observed on the preceding {@link DayOfWeek#FRIDAY} when they fall
   * on {@link DayOfWeek#SATURDAY}, or on the following {@link DayOfWeek#MONDAY} when they fall on
   * {@link DayOfWeek#SUNDAY}.
   *
   * <p>This strategy is useful for American holidays.
   */
  CLOSEST_WEEKDAY {
    @Override
    public LocalDate shift(LocalDate date) {
      LocalDate d = date;

      DayOfWeek dayOfWeek = date.getDayOfWeek();
      if (dayOfWeek == DayOfWeek.SATURDAY) {
        d = date.minusDays(1);
      } else if (dayOfWeek == DayOfWeek.SUNDAY) {
        d = date.plusDays(1);
      }

      return d;
    }

    @Override
    public LocalDate[] unshift(LocalDate date) {
      DayOfWeek dayOfWeek = date.getDayOfWeek();

      if (dayOfWeek == DayOfWeek.MONDAY) {
        return new LocalDate[] {date, date.minusDays(1)};
      } else if (dayOfWeek == DayOfWeek.FRIDAY) {
        return new LocalDate[] {date, date.plusDays(1)};
      }

      return new LocalDate[] {date};
    }
  },

  /**
   * For {@link Holiday}s that are observed on the following {@link DayOfWeek#MONDAY} when they fall
   * on {@link DayOfWeek#SATURDAY}, or on the following {@link DayOfWeek#TUESDAY} when they fall on
   * {@link DayOfWeek#SUNDAY}.
   *
   * <p>This strategy is useful for United Kingdom holidays.
   */
  PLUS_TWO_DAYS {
    @Override
    public LocalDate shift(LocalDate date) {
      DayOfWeek dayOfWeek = date.getDayOfWeek();
      return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
          ? date.plusDays(2)
          : date;
    }

    @Override
    public LocalDate[] unshift(LocalDate date) {
      DayOfWeek dayOfWeek = date.getDayOfWeek();
      return dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY
          ? new LocalDate[] {date, date.minusDays(2)}
          : new LocalDate[] {date};
    }
  },

  /**
   * For {@link Holiday}s that are observed on the following {@link DayOfWeek#MONDAY} when they fall
   * on {@link DayOfWeek#SUNDAY}.
   *
   * <p>This strategy is useful for US holidays.
   */
  SUNDAY_TO_MONDAY {
    @Override
    public LocalDate shift(LocalDate date) {
      return date.getDayOfWeek() == DayOfWeek.SUNDAY ? date.plusDays(1) : date;
    }

    @Override
    public LocalDate[] unshift(LocalDate date) {
      return date.getDayOfWeek() == DayOfWeek.MONDAY
          ? new LocalDate[] {date, date.minusDays(1)}
          : new LocalDate[] {date};
    }
  },

  /**
   * For {@link Holiday}s that are observed on the following {@link DayOfWeek#TUESDAY} when they
   * fall on {@link DayOfWeek#SUNDAY}.
   *
   * <p>This strategy is useful for Japanese holidays.
   */
  SUNDAY_TO_TUESDAY {
    @Override
    public LocalDate shift(LocalDate date) {
      return date.getDayOfWeek() == DayOfWeek.SUNDAY ? date.plusDays(2) : date;
    }

    @Override
    public LocalDate[] unshift(LocalDate date) {
      return date.getDayOfWeek() == DayOfWeek.TUESDAY
          ? new LocalDate[] {date, date.minusDays(2)}
          : new LocalDate[] {date};
    }
  },

  /**
   * For {@link Holiday}s that are observed on the following {@link DayOfWeek#WEDNESDAY} when they
   * fall on {@link DayOfWeek#SUNDAY}.
   *
   * <p>This strategy is useful for Japanese holidays.
   */
  SUNDAY_TO_WEDNESDAY {
    @Override
    public LocalDate shift(LocalDate date) {
      return date.getDayOfWeek() == DayOfWeek.SUNDAY ? date.plusDays(3) : date;
    }

    @Override
    public LocalDate[] unshift(LocalDate date) {
      return date.getDayOfWeek() == DayOfWeek.WEDNESDAY
          ? new LocalDate[] {date, date.minusDays(3)}
          : new LocalDate[] {date};
    }
  },

  /**
   * For {@link Holiday}s that are observed on the following {@link DayOfWeek#MONDAY} when they fall
   * on {@link DayOfWeek#SATURDAY} or {@link DayOfWeek#SUNDAY}.
   *
   * <p>This strategy is useful for Australian holidays.
   */
  WEEKEND_TO_MONDAY {
    @Override
    public LocalDate shift(LocalDate date) {
      LocalDate d = date;

      DayOfWeek dayOfWeek = date.getDayOfWeek();
      if (dayOfWeek == DayOfWeek.SATURDAY) {
        d = date.plusDays(2);
      } else if (dayOfWeek == DayOfWeek.SUNDAY) {
        d = date.plusDays(1);
      }

      return d;
    }

    @Override
    public LocalDate[] unshift(LocalDate date) {
      return date.getDayOfWeek() == DayOfWeek.MONDAY
          ? new LocalDate[] {date, date.minusDays(1), date.minusDays(2)}
          : new LocalDate[] {date};
    }
  };

  /**
   * Shift the given date.
   *
   * @param localDate a non-null date
   * @return a non-null date
   * @throws NullPointerException if the given date is {@code null}
   */
  public abstract LocalDate shift(LocalDate localDate);

  /**
   * Unshift the given date.
   *
   * @param localDate a non-null date
   * @return a non-null date
   * @throws NullPointerException if the given date is {@code null}
   */
  public abstract LocalDate[] unshift(LocalDate localDate);
}
