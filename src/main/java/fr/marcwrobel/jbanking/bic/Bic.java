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
package fr.marcwrobel.jbanking.bic;

import fr.marcwrobel.jbanking.IsoCountry;

import java.util.regex.Pattern;

/**
 * <p>
 * A Business Identifier Code (also known as BIC, SWIFT-BIC, BIC code, SWIFT ID or SWIFT code, Business Entity Identifier or BEI) as specified by ISO 9362:2009.
 * </p>
 *
 * <p>
 * A BIC is either eight (BIC8) or eleven (BIC11) characters made up of :<ul>
 * <li>4 letters: institution code (or bank code)</li>
 * <li>2 letters: ISO 3166-1 alpha-2 country code</li>
 * <li>2 letters or digits: location code</li>
 * <li>3 letters or digits (optional): branch code</li>
 * </ul>
 * Where an 8-digit code is given, it is assumed that it refers to the primary office. The primary office is always designated by the branch code {@value #PRIMARY_OFFICE_BRANCH_CODE}).
 * </p>
 *
 * <p>
 * This class is immutable.
 * </p>
 *
 * @author Marc Wrobel
 * @see <a href="http://wikipedia.org/wiki/Bank_Identifier_Code">http://wikipedia.org/wiki/Bank_Identifier_Code</a>
 * @since 1.0
 */
public final class Bic {

    /**
     * A simple regex that validate well-formed BIC.
     */
    public static final String BIC_REGEX = "[A-Za-z]{4}[A-Za-z]{2}[A-Za-z0-9]{2}([A-Za-z0-9]{3})?";

    /**
     * A pre-compiled Pattern for {@link #BIC_REGEX}.
     */
    public static final Pattern BIC_PATTERN = Pattern.compile(BIC_REGEX);

    /**
     * The branch code for primary offices.
     */
    public static final String PRIMARY_OFFICE_BRANCH_CODE = "XXX";

    /**
     * If the last character of the location code in a BIC is this one it means that the BIC is a Test BIC,
     */
    public static final char TEST_BIC_INDICATOR = '0';

    private static final int BIC8_LENGTH = 8;
    private static final int INSTITUTION_CODE_INDEX = 0;
    private static final int INSTITUTION_CODE_LENGTH = 4;
    private static final int COUNTRY_CODE_INDEX = INSTITUTION_CODE_INDEX + INSTITUTION_CODE_LENGTH;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int LOCATION_CODE_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
    private static final int LOCATION_CODE_LENGTH = 2;
    private static final int BRANCH_CODE_INDEX = LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH;
    private static final int BRANCH_CODE_LENGTH = 3;

    private final String normalizedBic;

    /**
     * Create a new bic from the given string.
     *
     * The given string may be a BIC8 or a BIC11.
     *
     * @param bic8Or11 A non null String.
     * @throws IllegalArgumentException if the given string is null
     * @throws BicFormatException       if the given BIC8 or BIC11 string does not match {@link #BIC_REGEX} or if the given BIC8 or BIC11 country code is not known in {@link fr.marcwrobel.jbanking.IsoCountry}
     */
    public Bic(final String bic8Or11) {
        if (bic8Or11 == null) {
            throw new IllegalArgumentException("the bic8Or11 argument cannot be null");
        }

        if (!isWellFormatted(bic8Or11)) {
            throw BicFormatException.forNotProperlyFormattedInput(bic8Or11);
        }

        if (!hasKnownCountryCode(bic8Or11)) {
            throw BicFormatException.forUnknownCountryCode(bic8Or11);
        }

        String cleanedBic = bic8Or11.toUpperCase();
        if (cleanedBic.length() == BIC8_LENGTH) {
            cleanedBic += PRIMARY_OFFICE_BRANCH_CODE;
        }

        this.normalizedBic = cleanedBic;
    }

    /**
     * Check whether or not the given string is valid BIC.
     *
     * @param bic A String.
     * @return {@code true} if the given string is valid BIC, otherwise {@code false}
     */
    public static boolean isValid(String bic) {
        return bic != null && isWellFormatted(bic) && hasKnownCountryCode(bic);
    }

    private static boolean isWellFormatted(String s) {
        return BIC_PATTERN.matcher(s).matches();
    }

    private static boolean hasKnownCountryCode(String s) {
        return IsoCountry.fromCode(s.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH)) != null;
    }

    /**
     * Extract the institution code (or bank code) from this BIC.
     *
     * @return A non null string representing this BIC institution code.
     */
    public String getInstitutionCode() {
        return normalizedBic.substring(INSTITUTION_CODE_INDEX, INSTITUTION_CODE_INDEX + INSTITUTION_CODE_LENGTH);
    }

    /**
     * Extract the country code from this BIC.
     *
     * @return A non null string representing this BIC country code.
     */
    public String getCountryCode() {
        return normalizedBic.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    /**
     * Extract the location code from this BIC.
     *
     * @return A non null string representing this BIC location code.
     */
    public String getLocationCode() {
        return normalizedBic.substring(LOCATION_CODE_INDEX, LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH);
    }

    /**
     * Extract the branch code from this BIC.
     *
     * @return A non null string representing this BIC branch code.
     */
    public String getBranchCode() {
        return normalizedBic.substring(BRANCH_CODE_INDEX, BRANCH_CODE_INDEX + BRANCH_CODE_LENGTH);
    }

    /**
     * Test whether or not this BIC is a test bic.
     *
     * <p>A BIC is a test BIC if the last character of the location code is {@value #TEST_BIC_INDICATOR}.</p>
     *
     * @return {@code true} if this BIC is a test BIC, otherwise {@code false}
     * @see #isLiveBic
     */
    public boolean isTestBic() {
        return normalizedBic.charAt(LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH - 1) == TEST_BIC_INDICATOR;
    }

    /**
     * Test whether or not this BIC is a live bic.
     *
     * <p>A BIC is a live BIC if the last character of the location code is not {@value #TEST_BIC_INDICATOR}.</p>
     *
     * @return {@code true} if this BIC is a live BIC, otherwise {@code false}
     * @see #isTestBic
     */
    public boolean isLiveBic() {
        return !isTestBic();
    }

    /**
     * Transform this BIC to a test BIC.
     *
     * @return this if this BIC is a test BIC, or this BIC corresponding test BIC otherwise.
     */
    public Bic asTestBic() {
        if (isTestBic()) {
            return this;
        }

        StringBuilder testBicBuilder = new StringBuilder(normalizedBic);
        testBicBuilder.setCharAt(LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH - 1, TEST_BIC_INDICATOR);
        return new Bic(testBicBuilder.toString());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * <p>To be equals to this one an other object must be a Bic and the BICs normalized form (see {@link #toString()}) must be equal.</p>
     *
     * @param o the object with which to compare.
     * @return {@code true} if this object is the same as the obj argument or {@code false} otherwise.
     * @see Object#toString()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return normalizedBic.equals(((Bic) o).normalizedBic);
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 * normalizedBic.hashCode();
    }

    /**
     * <p>Returns a normalized string representation of this BIC.</p>
     *
     * <p>Normalized means the string is:<ul>
     * <li>made of uppercase characters</li>
     * <li>eleven characters long (BIC11)</li>
     * </ul></p>
     *
     * @return a normalized string representation of this BIC
     */
    @Override
    public String toString() {
        return normalizedBic;
    }
}
