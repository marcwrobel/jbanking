package fr.marcwrobel.jbanking.internal;

/**
 * Represents a predicate (boolean-valued function) of one character argument. This is a
 * {@link FunctionalInterface functional interface} whose functional method is {@link #test(char)}.
 *
 * <p>
 * Note that {@link java.util.function.Predicate} could be used instead, but it would introduce unnecessary autoboxing
 * for {@code char} arguments.
 *
 * @since 4.0.0
 */
@FunctionalInterface
public interface CharacterPredicate {

  /**
   * Evaluates this predicate on the given character.
   *
   * @param c the input character
   * @return {@code true} if the input character matches the predicate, otherwise {@code false}
   */
  boolean test(char c);
}
