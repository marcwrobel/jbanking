package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A {@link Holiday} that has been punctually moved to another day.
 *
 * <p>
 * This class is useful for modeling holidays like the May Day in the United Kingdom, that has been exceptionally moved from
 * 2020-05-04 to 2020-05-08 in 2020 to coincide with Victory in Europe Day.
 *
 * @since 2.1.0
 */
public final class MovedHoliday implements Holiday {

  private final Holiday base;
  private final Map<LocalDate, LocalDate> replacements;
  private final Map<LocalDate, LocalDate> invertedReplacements;

  /**
   * Create a new holiday using the given base and replacements {@link Map}.
   *
   * <p>
   * The replacements are expressed as {@code k1, v1, k2, v2, ..., kn, vn} where kn are the original dates dans vn are the
   * replacement.
   *
   * @param base a non-null holiday to use as a base
   * @param replacements replacements, expressed as key / value
   * @throws NullPointerException if {@code base} is {@code null}
   */
  public MovedHoliday(Holiday base, LocalDate... replacements) {
    this.base = requireNonNull(base);
    this.replacements = new HashMap<>(0);
    this.invertedReplacements = new HashMap<>(0);

    for (int i = 0; i < replacements.length / 2; i++) {
      LocalDate from = replacements[i * 2];
      LocalDate to = replacements[i * 2 + 1];

      if (!from.equals(to)) {
        this.replacements.put(from, to);
        this.invertedReplacements.put(to, from);
      }
    }
  }

  /**
   * Create a new holiday using the given base {@link MonthDayHoliday}, the replacement day {@link MonthDay} and the years when
   * the replacement must occur.
   *
   * <p>
   * This constructor build a {@link MonthDayHoliday} internally.
   *
   * @param base a non-null holiday to use as a base.
   * @param replacement the replacement expressed as a {@link MonthDay}
   * @param replacedYears the years when the holiday must be replaced
   * @throws NullPointerException if {@code base} is {@code null}
   */
  public MovedHoliday(MonthDay base, MonthDay replacement, int... replacedYears) {
    this.base = new MonthDayHoliday(base);
    this.replacements = new HashMap<>(0);
    this.invertedReplacements = new HashMap<>(0);

    for (int year : replacedYears) {
      LocalDate from = base.atYear(year);
      LocalDate to = replacement.atYear(year);

      if (!from.equals(to)) {
        this.replacements.put(from, to);
        this.invertedReplacements.put(to, from);
      }
    }
  }

  /**
   * @see Holiday#check(LocalDate)
   */
  @Override
  public boolean check(LocalDate date) {
    if (replacements.containsKey(date)) {
      return false;
    }

    return base.check(invertedReplacements.getOrDefault(date, date));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MovedHoliday that = (MovedHoliday) o;
    return base.equals(that.base) && replacements.equals(that.replacements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(base, replacements);
  }

  @Override
  public String toString() {
    return "MovedHoliday{" + "base=" + base + ", replacements=" + replacements + '}';
  }
}
