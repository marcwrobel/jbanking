package fr.marcwrobel.jbanking.iban;

import static java.util.Objects.requireNonNull;

import java.util.Random;

/**
 * Generates pseudorandom {@link Iban IBANs}.
 *
 * <p>
 * Usage:
 *
 * <pre>
 * // Generating a random IBAN
 * Iban random1 = new RandomIban().next();
 *
 * // Generating a random IBAN using a given Random (in order to make the generation deterministic)
 * Iban random2 = new RandomIban(new Random(0)).next();
 *
 * // Generating a random french or german IBAN (with BbanStructure or IsoCountry)
 * Iban random3 = new RandomIban().next(BbanStructure.FR, BbanStructure.DE);
 * Iban random4 = new RandomIban().next(BbanStructure.valueOf(IsoCountry.FR.name()),
 *     BbanStructure.valueOf(IsoCountry.DE.name()));
 * </pre>
 *
 * <p>
 * This class should be used only for tests.
 */
public final class RandomIban {

  private final Random random;

  /**
   * Creates a new random Iban generator using the given {@link Random random number generator}.
   *
   * @param random a non-null {@link Random} instance
   * @throws NullPointerException if the given {@link Random} instance is {@code null}
   */
  public RandomIban(Random random) {
    this.random = requireNonNull(random);
  }

  /**
   * Creates a new random Iban generator.
   *
   * <p>
   * This constructor is creating a new {@link Random random number generator} each time it is invoked.
   */
  public RandomIban() {
    // Note that Random was choose over SecureRandom because security does not matter in our case and because Random :
    // - produces the same result on all platforms,
    // - produces the same results for a seed by default,
    // - is random enough,
    // - and is much faster.
    this(new Random());
  }

  /**
   * Generates a random IBAN for one of the existing {@link BbanStructure ISO 13616-compliant national IBAN formats}
   * (randomly chosen).
   *
   * @return a non-null {@link Iban}
   */
  public Iban next() {
    return next(BbanStructure.values());
  }

  /**
   * Generates a random IBAN for one of the given {@link BbanStructure ISO 13616-compliant national IBAN formats}
   * (randomly chosen).
   *
   * @return a non-null {@link Iban}
   * @throws NullPointerException if one of the given {@link BbanStructure} is {@code null}
   * @throws IllegalArgumentException if {@code structures} is empty
   */
  public Iban next(BbanStructure... structures) {
    BbanStructure structure = structures[random.nextInt(structures.length)];
    IbanPattern pattern = structure.getBbanPattern();

    StringBuilder bban = new StringBuilder(pattern.length);
    for (IbanPatternGroup group : pattern.groups) {
      for (int i = 0; i < group.length; i++) {
        String alphabet = group.characters.alphabet();
        bban.append(alphabet.charAt(random.nextInt(alphabet.length())));
      }
    }

    return new Iban(structure.getCountry(), bban.toString());
  }
}
