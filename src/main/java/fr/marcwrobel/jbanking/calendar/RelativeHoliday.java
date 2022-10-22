package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A {@link Holiday} relative to another {@link Holiday} with a fixed day shift.
 *
 * <p>
 * This class is modeling holidays like <a href="https://wikipedia.org/wiki/Easter_Monday">easter monday</a> or
 * <a href="https://wikipedia.org/wiki/Good_Friday">good friday</a>.
 *
 * @since 2.1.0
 */
public final class RelativeHoliday implements Holiday {

  private final Holiday base;
  private final int plusDays;

  /**
   * Create a new holiday using the given {@link Holiday base holiday} and the given number of days as the shift.
   *
   * @param base a non-null holiday to use as a base
   * @param plusDays a number of days to use as the shift (can be negative)
   * @throws NullPointerException if the given base is {@code null}
   */
  public RelativeHoliday(Holiday base, int plusDays) {
    this.base = requireNonNull(base);
    this.plusDays = plusDays;
  }

  /**
   * @see Holiday#check(LocalDate)
   */
  @Override
  public boolean check(LocalDate date) {
    return base.check(date.minusDays(plusDays));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RelativeHoliday that = (RelativeHoliday) o;
    return plusDays == that.plusDays && base.equals(that.base);
  }

  @Override
  public int hashCode() {
    return Objects.hash(base, plusDays);
  }

  @Override
  public String toString() {
    return "RelativeHoliday{" + "base=" + base + ", plusDays=" + plusDays + '}';
  }
}
