package fr.marcwrobel.jbanking.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * A {@link Holiday} based on two other holidays that adds a holiday when the previous and next days are holidays.
 *
 * <p>
 * This class is useful for modeling holidays like the bridged japanese holidays.
 *
 * @author Marc Wrobel
 * @since 3.1.0
 */
public class BridgedHoliday implements Holiday {

  private final Holiday first;
  private final Holiday second;

  public BridgedHoliday(Holiday first, Holiday second) {
    this.first = requireNonNull(first);
    this.second = requireNonNull(second);
  }

  @Override
  public boolean check(LocalDate date) {
    return first.check(date) || second.check(date)
        || (first.check(date.minusDays(1)) && second.check(date.plusDays(1)));
  }
}
