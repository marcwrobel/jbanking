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
package fr.marcwrobel.jbanking.creditor;

import com.google.common.collect.Sets;
import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.TestUtils;
import org.junit.Test;

import java.util.Set;

import static fr.marcwrobel.jbanking.TestUtils.shouldHaveThrown;
import static org.junit.Assert.*;

/**
 * Tests for the {@link fr.marcwrobel.jbanking.creditor.CreditorIdentifier} class.
 *
 * @author Charles Kayser
 */
public class CreditorIdentifierTest {

    private static final Set<String> VALID_CREDITOR_IDENTIFIERS = Sets.newHashSet(
            "HR04ZZZ01234567890",
            "SK19ZZZ70000000022",
            "NO38ZZZ123456785",
            "ES59ZZZX1234567L",
            "CY54ZZZ003A",
            "CZ56ZZZ12345",
            "IE84ZZZ123456",
            "FR72ZZZ123456",
            "PL18ZZZ0123456789",
            "DK95ZZZ999912345678",
            "DE51ZZZ12345678901",
            "LT30ZZZ123456789",
            "MC54ZZZ123456",
            "GB23ZZZSDDBARC000000ABCD1234",
            "SE41ZZZ1234567890",
            "ES04ZZZ52840790N",
            "NL42ZZZ123456780001",
            "AT61ZZZ01234567890",
            "LV21ZZZ40003000010",
            "BE68ZZZ0123456789",
            "GR44ZZZ12345",
            "IT66ZZZA1B2C3D4E5F6G7H8",
            "PT73ZZZ123456",
            "SI02ZZZ12345678",
            "HU56ZZZE12345676",
            "BE69ZZZ050D000000008",
            "HU74111A12345676",
            "BG07ZZZ100064095",
            "FI22BBB12345678",
            "SM94ZZZA1B2C3D4E5F6G7H8",
            "ES50ZZZM23456789",
            "LU27ZZZ0000000000123456789",
            "CH1312300000012345",
            "MT31ZZZ123456789X",
            "EE49ZZZEE00012345678"
    );

    private static final String VALID_CI_COUNTRY = "FR";
    private static final String VALID_CI_CHECKDIGIT = "72";
    private static final String VALID_CI_BUSINESS_CODE = "ZZZ";
    private static final String VALID_CI_NATIONAL_ID = "123456";
    private static final String VALID_CI = VALID_CI_COUNTRY + VALID_CI_CHECKDIGIT + VALID_CI_BUSINESS_CODE + VALID_CI_NATIONAL_ID;
    private static final String VALID_CI2 = "BE69ZZZ050D000000008";

    private static final String INVALID_CI_NATIONAL_ID = "132!";

    private static final String CI_WITH_INVALID_FORMAT = VALID_CI_COUNTRY + VALID_CI_CHECKDIGIT + VALID_CI_BUSINESS_CODE + INVALID_CI_NATIONAL_ID;
    private static final String CI_WITH_UNKNOWN_COUNTRY = "ZZ" + VALID_CI_CHECKDIGIT + VALID_CI_BUSINESS_CODE + VALID_CI_NATIONAL_ID;
    private static final String CI_WITH_UNSUPPORTED_COUNTRY = "US" + VALID_CI_CHECKDIGIT + VALID_CI_BUSINESS_CODE + VALID_CI_NATIONAL_ID;

    private static final String CI_WITH_INVALID_CHECK_DIGIT = VALID_CI_COUNTRY + "01" + VALID_CI_BUSINESS_CODE + VALID_CI_NATIONAL_ID;

    @Test
    public void nullIsNotAValidCreditorIdentifier() {
        assertFalse(CreditorIdentifier.isValid(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void aCreditorIdentifierCannotBeNull() {
        new CreditorIdentifier(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aCreditorIdentifierCountryCannotBeNull() {
        new CreditorIdentifier(null, VALID_CI_BUSINESS_CODE, "123456");
    }

    @Test(expected = IllegalArgumentException.class)
    public void aCreditorNationalIdCannotBeNull() {
        new CreditorIdentifier(IsoCountry.FRANCE, VALID_CI_BUSINESS_CODE, null);
    }

    @Test
    public void blankIsNotAValidCreditorIdentifier() {
        assertFalse(CreditorIdentifier.isValid(TestUtils.BLANK));
    }

    @Test(expected = CreditorIdentifierFormatException.class)
    public void aCreditorIdentifierCannotBeBlank() {
        new CreditorIdentifier(TestUtils.BLANK);
    }

    @Test(expected = CreditorIdentifierFormatException.class)
    public void aCreditorNationalIdCannotBeBlank() {
        new CreditorIdentifier(IsoCountry.FRANCE, VALID_CI_BUSINESS_CODE, TestUtils.BLANK);
    }

    @Test
    public void creditorIdWithUnknownCountryIsNotValid() {
        assertFalse(CreditorIdentifier.isValid(CI_WITH_UNKNOWN_COUNTRY));
    }

    @Test
    public void aCreditorIdMustBeFromAKnownCountry() {
        try {
            new CreditorIdentifier(CI_WITH_UNKNOWN_COUNTRY);
            shouldHaveThrown(CreditorIdentifierFormatException.class);
        } catch (CreditorIdentifierFormatException e) {
            assertEquals(CI_WITH_UNKNOWN_COUNTRY, e.getInputString());
            assertTrue(e.getMessage().contains("ISO 3166-1-alpha-2 code"));
        }
    }

    @Test
    public void creditorIdWithUnsupportedCountryIsNotValid() {
        assertFalse(CreditorIdentifier.isValid(CI_WITH_UNSUPPORTED_COUNTRY));
    }

    @Test
    public void notProperlyFormattedCreditorIdentifierIsNotValid() {
        assertFalse(CreditorIdentifier.isValid(CI_WITH_INVALID_FORMAT));
    }

    @Test
    public void aCreditorIdMustBeProperlyFormatted() {
        try {
            new CreditorIdentifier(CI_WITH_INVALID_FORMAT);
            shouldHaveThrown(CreditorIdentifierFormatException.class);
        } catch (CreditorIdentifierFormatException e) {
            assertEquals(CI_WITH_INVALID_FORMAT, e.getInputString());
            assertTrue(e.getMessage().contains("format"));
        }
    }

    @Test
    public void aCreditorNationalIdMustBeProperlyStructured() {
        try {
            new CreditorIdentifier(IsoCountry.FRANCE, VALID_CI_BUSINESS_CODE, INVALID_CI_NATIONAL_ID);
            shouldHaveThrown(CreditorIdentifierFormatException.class);
        } catch (CreditorIdentifierFormatException e) {
            assertEquals(INVALID_CI_NATIONAL_ID, e.getInputString());
            assertTrue(e.getMessage().contains("format"));
        }
    }

    @Test
    public void aCreditorIdWithInvalidCheckDigitsIsNotValid() {
        assertFalse(CreditorIdentifier.isValid(CI_WITH_INVALID_CHECK_DIGIT));
    }

    @Test
    public void aCreditorIdMustHaveCorrectCheckDigit() {
        try {
            new CreditorIdentifier(CI_WITH_INVALID_CHECK_DIGIT);
            shouldHaveThrown(CreditorIdentifierFormatException.class);
        } catch (CreditorIdentifierFormatException e) {
            assertEquals(CI_WITH_INVALID_CHECK_DIGIT, e.getInputString());
            assertTrue(e.getMessage().contains("check digits"));
        }
    }

    @Test
    public void validCreditorIdentifierDecomposition() {
        assertTrue(CreditorIdentifier.isValid(VALID_CI));
        CreditorIdentifier creditorId = new CreditorIdentifier(VALID_CI);
        assertEquals(VALID_CI_COUNTRY, creditorId.getCountryCode());
        assertEquals(VALID_CI_CHECKDIGIT, creditorId.getCheckDigit());
        assertEquals(VALID_CI_BUSINESS_CODE, creditorId.getBusinessCode());
        assertEquals(VALID_CI_NATIONAL_ID, creditorId.getNationalIdentifier());
    }

    @Test
    public void validCreditorIdentifiersTest() {
        for (String creditorIdString : VALID_CREDITOR_IDENTIFIERS) {
            assertTrue(String.format("%s should be valid", creditorIdString), CreditorIdentifier.isValid(creditorIdString));

            CreditorIdentifier creditorId = new CreditorIdentifier(creditorIdString);
            String countryCode = creditorIdString.substring(0, 2);
            String businessCode = creditorIdString.substring(4, 7);
            String nationalId = creditorIdString.substring(7);
            assertEquals(String.format("%s not properly calculated", creditorId), creditorId, new CreditorIdentifier(IsoCountry.fromCode(countryCode), businessCode, nationalId));
        }
    }

    @Test
    public void creditorIdValidationIsNotCaseSensitive() {
        for (String creditorId : VALID_CREDITOR_IDENTIFIERS) {
            String lowerCaseCreditorIdentifier = creditorId.toLowerCase();
            assertTrue(String.format("%s should be valid", lowerCaseCreditorIdentifier), CreditorIdentifier.isValid(lowerCaseCreditorIdentifier));
        }
    }

    @Test
    public void creditorIdCreationIsNotCaseSensitive() {
        for (String creditorId : VALID_CREDITOR_IDENTIFIERS) {
            new CreditorIdentifier(creditorId.toLowerCase());
        }
    }

    @Test
    public void creditorIdFromBbanCreationIsNotCaseSensitive() {
        for (String creditorId : VALID_CREDITOR_IDENTIFIERS) {
            String countryCode = creditorId.substring(0, 2);
            String businessCode = creditorId.substring(4, 7);
            String nationalId = creditorId.substring(7);
            assertEquals(String.format("%s not properly calculated", creditorId), creditorId, new CreditorIdentifier(IsoCountry.fromCode(countryCode), businessCode, nationalId.toLowerCase()).toString());
        }
    }

    @Test
    public void printableCreditorIdentifiersAreValid() {
        CreditorIdentifier creditorId = new CreditorIdentifier(VALID_CI);
        String printableCreditorIdentifier = creditorId.toString();

        assertTrue(CreditorIdentifier.isValid(printableCreditorIdentifier));
        assertEquals(creditorId, new CreditorIdentifier(printableCreditorIdentifier));
    }

    @Test
    public void equalityTest() {
        CreditorIdentifier creditorId1 = new CreditorIdentifier(VALID_CI);
        CreditorIdentifier creditorId2 = new CreditorIdentifier(creditorId1.toString());
        CreditorIdentifier creditorId3 = new CreditorIdentifier(VALID_CI.toLowerCase());

        assertTrue(creditorId1.equals(creditorId1));
        assertTrue(creditorId2.equals(creditorId2));
        assertTrue(creditorId3.equals(creditorId3));

        assertTrue(creditorId1.equals(creditorId2));
        assertTrue(creditorId2.equals(creditorId1));
        assertTrue(creditorId2.equals(creditorId3));
        assertTrue(creditorId3.equals(creditorId2));
        assertTrue(creditorId1.equals(creditorId3));
        assertTrue(creditorId3.equals(creditorId1));
        assertTrue(creditorId1.hashCode() == creditorId2.hashCode());
        assertTrue(creditorId2.hashCode() == creditorId3.hashCode());

        assertFalse(creditorId1.equals(null));
        assertFalse(creditorId1.equals(new Object()));
        assertFalse(creditorId1.equals(new CreditorIdentifier(VALID_CI2)));
    }

}
