package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A {@link Holiday} that has been punctually suppressed.
 *
 * <p>This class is useful for modeling holidays like the Spring bank holiday in the united kingdom,
 * that has been exceptionally suppressed in 2002 and 2012 for the golden and diamond jubilee.
 *
 * @author Marc Wrobel
 * @since 2.1.0
 */
public final class SuppressedHoliday implements Holiday {

  private final Holiday base;
  private final Set<Integer> suppressedYears;

  /**
   * Create a new holiday using the given base and suppressed years.
   *
   * <p>The replacements are expressed as {@code k1, v1, k2, v2, ..., kn, vn} where kn are the
   * original dates dans vn are the replacement.
   *
   * @param base a non-null holiday to use as a base.
   * @param suppressedYears a list of year the holiday has been suppressed
   * @throws NullPointerException if <code>base</code> is null
   */
  public SuppressedHoliday(Holiday base, Collection<Integer> suppressedYears) {
    this.base = requireNonNull(base);
    this.suppressedYears = new HashSet<>(suppressedYears);
  }

  /**
   * Create a new holiday using the given base and suppressed years.
   *
   * <p>The replacements are expressed as {@code k1, v1, k2, v2, ..., kn, vn} where kn are the
   * original dates dans vn are the replacement.
   *
   * @param base a non-null holiday to use as a base.
   * @param suppressedYears a list of year the holiday has been suppressed
   * @throws NullPointerException if <code>base</code> is null
   */
  public SuppressedHoliday(Holiday base, Integer... suppressedYears) {
    this(base, Arrays.asList(suppressedYears));
  }

  /** @see Holiday#check(LocalDate) */
  @Override
  public boolean check(LocalDate date) {
    if (suppressedYears.contains(date.getYear())) {
      return false;
    }

    return base.check(date);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SuppressedHoliday that = (SuppressedHoliday) o;
    return base.equals(that.base) && suppressedYears.equals(that.suppressedYears);
  }

  @Override
  public int hashCode() {
    return Objects.hash(base, suppressedYears);
  }

  @Override
  public String toString() {
    return "SuppressedHoliday{" + "base=" + base + ", suppressedYears=" + suppressedYears + '}';
  }
}
