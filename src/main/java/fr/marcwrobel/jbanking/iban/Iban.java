/**
 * Copyright 2013 Marc Wrobel (marc.wrobel@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.marcwrobel.jbanking.iban;

import fr.marcwrobel.jbanking.IsoCountry;

import java.util.regex.Pattern;

/**
 * <p>
 * An International Bank Account Number (IBAN) Code as specified by the
 * <a href="http://www.swift.com/solutions/messaging/information_products/directory_products/iban_format_registry/index.page">ISO 13616:2007 standard</a>.
 * </p>
 *
 * <p>
 * An IBAN consists of a two-letter ISO 3166-1 country code, followed by two check digits and up to thirty alphanumeric characters for a BBAN
 * (Basic Bank Account Number) which has a fixed length per country and, included within it, a bank identifier with a fixed position and a fixed length per country.
 * The check digits are calculated based on the scheme defined in ISO/IEC 7064 (MOD97-10).
 * Also note that an IBAN is case insensitive.
 * </p>
 *
 * <p>
 * This class handles validation of the check digit and validation of the {@link BbanStructure BBAN structure} (based on information specified in the IBAN
 * registry Release 45 of may 2013).
 * </p>
 *
 * <p>Instances of this class are immutable and are safe for use by multiple concurrent threads.</p>
 *
 * @author Marc Wrobel
 * @see BbanStructure
 * @see <a href="http://wikipedia.org/wiki/International_Bank_Account_Number">http://en.wikipedia.org/wiki/International_Bank_Account_Number</a>
 * @see <a href="http://www.swift.com/solutions/messaging/information_products/directory_products/iban_format_registry/index.page">IBAN registry</a>
 * @since 1.0
 */
public final class Iban {

    private static final String BASIC_REGEX = "[A-Za-z]{2}[0-9]{2}[A-Za-z0-9]+";
    private static final Pattern BASIC_PATTERN = Pattern.compile(BASIC_REGEX);

    private static final int COUNTRY_CODE_INDEX = 0;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int CHECK_DIGITS_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
    private static final int CHECK_DIGITS_LENGTH = 2;
    private static final int BBAN_INDEX = CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH;

    private static final int GROUP_SIZE_FOR_PRINTABLE_IBAN = 4;

    private final String iban;

    /**
     * Create a new IBAN from the given country code and BBAN.
     *
     * @param country A non null IsoCountry.
     * @param bban    A non null String.
     * @throws IllegalArgumentException if either the IsoCountry or BBAN is null
     * @throws IbanFormatException      if a valid IBAN could not be calculated using the given IsoCountry and BBAN
     */
    public Iban(IsoCountry country, String bban) {
        if (country == null) {
            throw new IllegalArgumentException("the country argument cannot be null");
        }

        if (bban == null) {
            throw new IllegalArgumentException("the bban argument cannot be null");
        }

        String normalizedBban = normalize(bban);
        String normalizedIban = country.getCode() + "00" + normalizedBban;

        BbanStructure bbanStructure = BbanStructure.forCountry(country);
        if (bbanStructure == null) {
            throw IbanFormatException.forNotSupportedCountry(bban, country);
        }

        if (!bbanStructure.isBbanValid(normalizedBban)) {
            throw IbanFormatException.forInvalidBbanStructure(bban, bbanStructure);
        }

        String checkDigits = IbanCheckDigit.INSTANCE.calculate(normalizedIban);

        this.iban = country.getCode() + checkDigits + normalizedBban;
    }

    /**
     * Create a new IBAN from the given string.
     *
     * @param iban A non null String.
     * @throws IllegalArgumentException if the given string is null
     * @throws IbanFormatException      if the given string is not a valid IBAN
     */
    public Iban(String iban) {
        if (iban == null) {
            throw new IllegalArgumentException("the iban argument cannot be null");
        }

        String normalizedIban = normalize(iban);

        if (!isWellFormatted(normalizedIban)) {
            throw IbanFormatException.forNotProperlyFormattedInput(normalizedIban);
        }

        IsoCountry country = findCountryFor(normalizedIban);
        if (country == null) {
            throw IbanFormatException.forUnknownCountry(iban);
        }

        BbanStructure bbanStructure = BbanStructure.forCountry(country);
        if (bbanStructure == null) {
            throw IbanFormatException.forNotSupportedCountry(iban, country);
        }

        if (!bbanStructure.isBbanValid(normalizedIban.substring(BBAN_INDEX))) {
            throw IbanFormatException.forInvalidBbanStructure(iban, bbanStructure);
        }

        if (!IbanCheckDigit.INSTANCE.validate(normalizedIban)) {
            throw IbanFormatException.forIncorrectCheckDigits(iban);
        }

        this.iban = normalizedIban;
    }

    /**
     * Validates the given IBAN String.
     *
     * @param iban A String.
     * @return {@code true} if the given String is a valid IBAN, {@code false} otherwise.
     */
    public static boolean isValid(String iban) {
        if (iban == null) {
            return false;
        }

        String normalizedIban = normalize(iban);

        if (!isWellFormatted(normalizedIban)) {
            return false;
        }

        IsoCountry country = findCountryFor(normalizedIban);
        if (country == null) {
            return false;
        }

        BbanStructure bbanStructure = BbanStructure.forCountry(country);
        if (bbanStructure == null) {
            return false;
        }

        if (!bbanStructure.isBbanValid(normalizedIban.substring(BBAN_INDEX))) {
            return false;
        }

        if (!IbanCheckDigit.INSTANCE.validate(normalizedIban)) {
            return false;
        }

        return true;
    }

    private static String normalize(String iban) {
        return iban.replaceAll("\\s+", "").toUpperCase();
    }

    private static boolean isWellFormatted(String s) {
        return BASIC_PATTERN.matcher(s).matches();
    }

    private static IsoCountry findCountryFor(String s) {
        return IsoCountry.fromCode(s.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH));
    }

    /**
     * Extract the ISO 3166-1-alpha-2 country code from this IBAN.
     *
     * @return A non null string representing this IBAN ISO 3166-1-alpha-2 country code.
     */
    public String getCountryCode() {
        return iban.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    /**
     * Extract the check digit from this IBAN.
     *
     * @return A non null string representing this IBAN check digit.
     */
    public String getCheckDigit() {
        return iban.substring(CHECK_DIGITS_INDEX, CHECK_DIGITS_INDEX + CHECK_DIGITS_LENGTH);
    }

    /**
     * Extract the BBAN from this IBAN.
     *
     * @return A non null string representing this IBAN BBAN.
     */
    public String getBban() {
        return iban.substring(BBAN_INDEX);
    }

    /**
     * Gets the printable version of this IBAN.
     *
     * <p>
     * When printed on paper, the IBAN is expressed in groups of four characters separated by a single space, the last group being of variable length
     * </p>
     *
     * @return A non null string representing this IBAN formatted for printing.
     */
    public String toPrintableString() {
        StringBuilder printableIban = new StringBuilder(iban);
        int length = iban.length();

        for (int i = 0; i < length / GROUP_SIZE_FOR_PRINTABLE_IBAN; i++) {
            printableIban.insert((i + 1) * GROUP_SIZE_FOR_PRINTABLE_IBAN + i, ' ');
        }

        return printableIban.toString();
    }

    @Override
    public String toString() {
        return iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Iban other = (Iban) o;
        if (!iban.equals(other.iban)) {
            return false;
        }

        return true;
    }

    /**
     * <p>Returns a normalized string representation of this IBAN.</p>
     *
     * <p>Normalized means the string is:<ul>
     * <li>made of uppercase characters</li>
     * <li>contains no spaces</li>
     * </ul></p>
     *
     * @return a normalized string representation of this IBAN
     */
    @Override
    public int hashCode() {
        return 29 * iban.hashCode();
    }
}