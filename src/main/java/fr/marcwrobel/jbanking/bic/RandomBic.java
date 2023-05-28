package fr.marcwrobel.jbanking.bic;

import static fr.marcwrobel.jbanking.swift.SwiftPatternCharacterRepresentation.DIGITS;
import static fr.marcwrobel.jbanking.swift.SwiftPatternCharacterRepresentation.UPPER_CASE_LETTERS;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.IsoCurrency;
import java.util.Arrays;
import java.util.Random;

/**
 * Generates pseudorandom {@link Bic BICs}.
 *
 * <p>
 * Usage:
 *
 * <pre>
 * // Generating a random BIC
 * Bic random1 = new RandomBic().next();
 *
 * // Generating a random BIC using a given Random (in order to make the generation deterministic)
 * Bic random2 = new RandomBic(new Random(0)).next();
 *
 * // Generating a random french or german BIC
 * Bic random3 = new RandomBic().next(IsoCountry.FR, IsoCountry.DE);
 * Bic random4 = new RandomBic().next("FR", "DE");
 *
 * // Generating a random IBAN for a country using Euro or US Dollar as currency.
 * Iban random5 = new RandomIban().next(IsoCurrency.EUR, IsoCurrency.USD);
 * </pre>
 *
 * <p>
 * This class should only be used for tests.
 *
 * @since 4.2.0
 */
public class RandomBic {

  private static final String LETTERS = UPPER_CASE_LETTERS.alphabet();
  private static final String LETTERS_AND_DIGITS = LETTERS + DIGITS.alphabet();

  private final Random random;

  /**
   * Creates a new random BIC generator using the given {@link Random random number generator}.
   *
   * @param random a non-null {@link Random} instance
   * @throws NullPointerException if the given {@link Random} instance is {@code null}
   */
  public RandomBic(Random random) {
    this.random = requireNonNull(random);
  }

  /**
   * Creates a new random BIC generator.
   *
   * <p>
   * This constructor is creating a new {@link Random random number generator} each time it is
   * invoked.
   */
  public RandomBic() {
    // Note that Random was chosen over SecureRandom because security does not matter in our case and because Random :
    // - produces the same result on all platforms,
    // - produces the same results for a seed by default,
    // - is random enough,
    // - and is much faster.
    this(new Random());
  }

  public Bic next() {
    return next(IsoCountry.values());
  }

  /**
   * Generates a random BIC for one of the given {@link IsoCountry country} (randomly chosen).
   *
   * @param countries a non-null and non-empty array of {@link IsoCountry}
   * @return a non-null {@link Bic}
   * @throws NullPointerException if {@code countries} is null
   * @throws IllegalArgumentException if {@code countries} is empty
   */
  public Bic next(IsoCountry... countries) {
    IsoCountry country = countries[random.nextInt(countries.length)];
    return generate(country);
  }

  /**
   * Generates a random BIC for one of the given ISO country alpha-2 codes (randomly chosen).
   *
   * @param isoCountryAlpha2Codes a non-null and non-empty array of ISO country alpha-2 codes
   * @return a non-null {@link Bic}
   * @throws IllegalArgumentException if {@code isoCountryAlpha2Codes} is empty or if no corresponding {@link IsoCountry} can be
   *         found for the chosen ISO country alpha-2 code
   */
  public Bic next(String... isoCountryAlpha2Codes) {
    String countryCode = isoCountryAlpha2Codes[random.nextInt(isoCountryAlpha2Codes.length)];

    IsoCountry country = IsoCountry.fromAlpha2Code(countryCode).orElseThrow(() -> new IllegalArgumentException(
        format("no corresponding country could be found for alpha-2 code '%s'", countryCode)));

    return generate(country);
  }

  /**
   * Generates a random BIC for one of the given {@link IsoCurrency currency} (randomly chosen).
   * <br>
   * This method is not efficient: it needs to build a sorted array of all currencies' countries each time it is invoked.
   *
   * @param currencies a non-null and non-empty array of {@link IsoCurrency}
   * @return a non-null {@link Bic}
   * @throws IllegalArgumentException if {@code currencies} is empty.
   */
  public Bic next(IsoCurrency... currencies) {
    IsoCountry[] countries = Arrays.stream(currencies)
        .flatMap(currency -> currency.getCountries().stream())
        .sorted()
        .toArray(IsoCountry[]::new);
    return next(countries);
  }

  private Bic generate(IsoCountry country) {
    StringBuilder bic = new StringBuilder(Bic.BIC11_LENGTH);

    for (int i = 0; i < Bic.INSTITUTION_CODE_LENGTH; i++) {
      bic.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
    }

    bic.append(country.getAlpha2Code());

    for (int i = 0; i < Bic.LOCATION_CODE_LENGTH + Bic.BRANCH_CODE_LENGTH; i++) {
      bic.append(LETTERS_AND_DIGITS.charAt(random.nextInt(LETTERS_AND_DIGITS.length())));
    }

    return new Bic(bic.toString());
  }
}
