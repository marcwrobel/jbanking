package fr.marcwrobel.jbanking.iban;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.IsoCurrency;
import java.util.Arrays;
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
 *
 * @since 4.0.0
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
   * This constructor is creating a new {@link Random random number generator} each time it is
   * invoked.
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
   * Generates a random IBAN for one of the existing
   * {@link BbanStructure ISO 13616-compliant national IBAN formats} (randomly chosen).
   *
   * @return a non-null {@link Iban}
   */
  public Iban next() {
    return next(BbanStructure.values());
  }

  /**
   * Generates a random IBAN for one of the given
   * {@link BbanStructure ISO 13616-compliant national IBAN formats} (randomly chosen).
   *
   * @param structures a non-null and non-empty array of {@link BbanStructure}
   * @return a non-null {@link Iban}
   * @throws NullPointerException if one of the given {@link BbanStructure} is {@code null}
   * @throws IllegalArgumentException if {@code structures} is empty
   */
  public Iban next(BbanStructure... structures) {
    BbanStructure structure = structures[random.nextInt(structures.length)];
    return generate(structure);
  }

  /**
   * Generates a random IBAN for one of the given {@link IsoCountry country} (randomly chosen).
   *
   * @param countries a non-null and non-empty array of {@link IsoCountry}
   * @return a non-null {@link Iban}
   * @throws IllegalArgumentException if {@code structures} is empty or if no corresponding {@link BbanStructure} can be found
   *         for the chosen {@link IsoCountry}
   * @since 4.2.0
   */
  public Iban next(IsoCountry... countries) {
    IsoCountry country = countries[random.nextInt(countries.length)];

    BbanStructure structure = BbanStructure.forCountry(country).orElseThrow(() -> new IllegalArgumentException(
        format("no corresponding structure could be found for country '%s'", country)));

    return generate(structure);
  }

  /**
   * Generates a random IBAN for one of the given ISO country alpha-2 codes (randomly chosen).
   *
   * @param isoCountryAlpha2Codes a non-null and non-empty array of ISO country alpha-2 codes
   * @return a non-null {@link Iban}
   * @throws IllegalArgumentException if {@code isoCountryAlpha2Codes} is empty or if no corresponding {@link IsoCountry} and
   *         {@link BbanStructure} can be found for the chosen ISO country alpha-2 code
   * @since 4.2.0
   */
  public Iban next(String... isoCountryAlpha2Codes) {
    String countryCode = isoCountryAlpha2Codes[random.nextInt(isoCountryAlpha2Codes.length)];

    IsoCountry country = IsoCountry.fromAlpha2Code(countryCode).orElseThrow(() -> new IllegalArgumentException(
        format("no corresponding country could be found for alpha-2 code '%s'", countryCode)));
    BbanStructure structure = BbanStructure.forCountry(country).orElseThrow(() -> new IllegalArgumentException(
        format("no corresponding structure could be found for country '%s'", country)));

    return generate(structure);
  }

  /**
   * Generates a random IBAN for one of the given {@link IsoCurrency currency} (randomly chosen).
   * <br>
   * This method is not efficient: it needs to build a sorted array of all currencies' countries and filter non-IBAN countries
   * each time it is invoked.
   *
   * @param currencies a non-null and non-empty array of {@link IsoCurrency}
   * @return a non-null {@link Iban}
   * @throws IllegalArgumentException if {@code currencies} is empty or if no corresponding {@link BbanStructure} can be found
   *         for the chosen {@link IsoCurrency}'s countries.
   * @since 4.2.0
   */
  public Iban next(IsoCurrency... currencies) {
    IsoCountry[] countries = Arrays.stream(currencies)
        .flatMap(currency -> currency.getCountries().stream())
        .filter(country -> BbanStructure.forCountry(country).isPresent())
        .sorted()
        .toArray(IsoCountry[]::new);
    return next(countries);
  }

  private Iban generate(BbanStructure structure) {
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
