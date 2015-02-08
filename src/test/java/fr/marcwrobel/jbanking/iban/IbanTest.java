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

import com.google.common.collect.Sets;
import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.TestUtils;
import org.junit.Test;

import java.util.Set;

import static fr.marcwrobel.jbanking.TestUtils.shouldHaveThrown;
import static org.junit.Assert.*;

/**
 * Tests for the {@link Iban} class.
 *
 * @author Marc Wrobel
 */
public class IbanTest {

    private static final Set<String> VALID_IBANS = Sets.newHashSet(
            "AL36442788709271283994894168",
            "AD9179714843548170724658",
            "AT836670643070032585",
            "AZ73GIGY95609694664952978687",
            "BH45BSND64749329633519",
            "BE83158799706920",
            "BA590483797190961278",
            "BR2900994781520950149594104E9",
            "BG29EFYA04741506522965",
            "CR0246443699441663799",
            "HR7971488917057910183",
            "CY75371695368399798483913159",
            "CZ9667197451460125048740",
            "DK2017303660295651",
            "DO72758351294497410197174992",
            "EE803801541625441184",
            "FO5778020271560713",
            "FI6346426364591804",
            "FR2531682128768051490609537",
            "GE20NB4430283848566197",
            "DE09355072686373974067",
            "GI44UQCR057428239600992",
            "GR7642039964850941669463374",
            "GL0502210301477863",
            "GT46866930583377769763582334",
            "HU91584374853132521152169392",
            "IS925454646268060577432601",
            "IE27CTJG26368089744633",
            "IL862354629185675963157",
            "IT82K7579579031831283849724",
            "KZ830907614112967961",
            "KW23YOUF8981762754308793114869",
            "LV88HAGO9615590045208",
            "LB52372840451692007088329912",
            "LI1935676368061346475",
            "LT192412904756279442",
            "LU951073196477746242",
            "MK31948483764450559",
            "MT84AIWA00813109843252965695890",
            "MR5442207381102036325668909",
            "MU65OSUR4348845783494200142ABC",
            "MD9881991481700015080266",
            "MC3212096543766737867569392",
            "ME36752723963983139414",
            "NL64VCYA2607250706",
            "NO2451742753161",
            "PK13LCJY3078553597017331",
            "PL64778828144458067930515057",
            "PS38LEKA813957891201287138525",
            "PT87847144161279936872891",
            "RO17KRBO3212835705756123",
            "SM67H0718392993037614346095",
            "SA0979413508515371577583",
            "RS80246113647338221492",
            "SK7336568401664473733427",
            "SI75412441865827872",
            "ES2837832292261368335005",
            "SE0651297191201320278580",
            "CH2669331784946419826",
            "TN6199977105796904072050",
            "TR888859050625760496700846",
            "AE532299249995935421750",
            "GB42RHBR54612317078692",
            "VG14NDUM4605555206975725",
            "JO94CBJO0010000000000131000302",
            "QA58DOHB00001234567890ABCDEFG",
            "TL380080012345678910157"
    );

    private static final String VALID_IBAN_COUNTRY = "FR";
    private static final String VALID_IBAN_CHECKDIGIT = "14";
    private static final String VALID_IBAN_BBAN = "20041010050500013M02606";
    private static final String VALID_IBAN = VALID_IBAN_COUNTRY + VALID_IBAN_CHECKDIGIT + VALID_IBAN_BBAN;
    private static final String VALID_IBAN2 = "AL36442788709271283994894168";

    private static final String BBAN_WITH_INVALID_FORMAT = "132!";
    private static final String IBAN_WITH_INVALID_FORMAT = VALID_IBAN_COUNTRY + VALID_IBAN_CHECKDIGIT + BBAN_WITH_INVALID_FORMAT;

    private static final String IBAN_WITH_UNKNOWN_COUNTRY = "ZZ" + VALID_IBAN_CHECKDIGIT + VALID_IBAN_BBAN;
    private static final String IBAN_WITH_UNSUPPORTED_COUNTRY = "US" + VALID_IBAN_CHECKDIGIT + VALID_IBAN_BBAN;
    private static final String IBAN_WITH_INVALID_CHECK_DIGIT = VALID_IBAN_COUNTRY + "01" + VALID_IBAN_BBAN;

    private static final String IBAN_WITH_INVALID_BBAN_STRUCTURE = "GB72MIDLA0051539024150";

    @Test
    public void nullIsNotAValidIban() {
        assertFalse(Iban.isValid(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void anIbanCannotBeNull() {
        new Iban(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void anIbanCountryCannotBeNull() {
        new Iban(null, "123456");
    }

    @Test(expected = IllegalArgumentException.class)
    public void anIbanBbanCannotBeNull() {
        new Iban(IsoCountry.FRANCE, null);
    }

    @Test
    public void blankIsNotAValidIban() {
        assertFalse(Iban.isValid(TestUtils.BLANK));
    }

    @Test(expected = IbanFormatException.class)
    public void anIbanCannotBeBlank() {
        new Iban(TestUtils.BLANK);
    }

    @Test(expected = IbanFormatException.class)
    public void anIbanBbanCannotBeBlank() {
        new Iban(IsoCountry.FRANCE, TestUtils.BLANK);
    }

    @Test
    public void ibanWithUnknownCountryIsNotValid() {
        assertFalse(Iban.isValid(IBAN_WITH_UNKNOWN_COUNTRY));
    }

    @Test
    public void anIbanMustBeFromAKnownCountry() {
        try {
            new Iban(IBAN_WITH_UNKNOWN_COUNTRY);
            shouldHaveThrown(IbanFormatException.class);
        } catch (IbanFormatException e) {
            assertEquals(IBAN_WITH_UNKNOWN_COUNTRY, e.getInputString());
            assertTrue(e.getMessage().contains("ISO 3166-1-alpha-2 code"));
        }
    }

    @Test
    public void ibanWithUnsupportedCountryIsNotValid() {
        assertFalse(Iban.isValid(IBAN_WITH_UNSUPPORTED_COUNTRY));
    }

    @Test
    public void anIbanMustBeFromASupportedCountry() {
        try {
            new Iban(IBAN_WITH_UNSUPPORTED_COUNTRY);
            shouldHaveThrown(IbanFormatException.class);
        } catch (IbanFormatException e) {
            assertEquals(IBAN_WITH_UNSUPPORTED_COUNTRY, e.getInputString());
            assertTrue(e.getMessage().contains("support"));
        }
    }

    @Test
    public void anIbanCountryMustBeSupported() {
        try {
            new Iban(IsoCountry.UNITED_STATES, VALID_IBAN_BBAN);
            shouldHaveThrown(IbanFormatException.class);
        } catch (IbanFormatException e) {
            assertEquals(VALID_IBAN_BBAN, e.getInputString());
            assertTrue(e.getMessage().contains("support"));
        }
    }

    @Test
    public void notProperlyFormattedIbanIsNotValid() {
        assertFalse(Iban.isValid(IBAN_WITH_INVALID_FORMAT));
    }

    @Test
    public void anIbanMustBeProperlyFormatted() {
        try {
            new Iban(IBAN_WITH_INVALID_FORMAT);
            shouldHaveThrown(IbanFormatException.class);
        } catch (IbanFormatException e) {
            assertEquals(IBAN_WITH_INVALID_FORMAT, e.getInputString());
            assertTrue(e.getMessage().contains("format"));
        }
    }

    @Test
    public void notProperlyStructuredIbanIsNotValid() {
        assertFalse(Iban.isValid(IBAN_WITH_INVALID_BBAN_STRUCTURE));
    }

    @Test
    public void anIbanMustBeProperlyStructured() {
        try {
            new Iban(IBAN_WITH_INVALID_BBAN_STRUCTURE);
            shouldHaveThrown(IbanFormatException.class);
        } catch (IbanFormatException e) {
            assertEquals(IBAN_WITH_INVALID_BBAN_STRUCTURE, e.getInputString());
            assertTrue(e.getMessage().contains("structure"));
        }
    }

    @Test
    public void anIbanBbanMustBeProperlyStructured() {
        try {
            new Iban(IsoCountry.FRANCE, BBAN_WITH_INVALID_FORMAT);
            shouldHaveThrown(IbanFormatException.class);
        } catch (IbanFormatException e) {
            assertEquals(BBAN_WITH_INVALID_FORMAT, e.getInputString());
            assertTrue(e.getMessage().contains("structure"));
        }
    }

    @Test
    public void anIbanWithInvalidCheckDigitsIsNotValid() {
        assertFalse(Iban.isValid(IBAN_WITH_INVALID_CHECK_DIGIT));
    }

    @Test
    public void anIbanMustHaveCorrectCheckDigit() {
        try {
            new Iban(IBAN_WITH_INVALID_CHECK_DIGIT);
            shouldHaveThrown(IbanFormatException.class);
        } catch (IbanFormatException e) {
            assertEquals(IBAN_WITH_INVALID_CHECK_DIGIT, e.getInputString());
            assertTrue(e.getMessage().contains("check digits"));
        }
    }

    @Test
    public void validIbanDecomposition() {
        assertTrue(Iban.isValid(VALID_IBAN));
        Iban iban = new Iban(VALID_IBAN);
        assertEquals(VALID_IBAN_COUNTRY, iban.getCountryCode());
        assertEquals(VALID_IBAN_CHECKDIGIT, iban.getCheckDigit());
        assertEquals(VALID_IBAN_BBAN, iban.getBban());
    }

    @Test
    public void validIbansTest() {
        for (String ibanString : VALID_IBANS) {
            assertTrue(String.format("%s should be valid", ibanString), Iban.isValid(ibanString));

            Iban iban = new Iban(ibanString);
            String countryCode = ibanString.substring(0, 2);
            String bban = ibanString.substring(4);
            assertEquals(String.format("%s not properly calculated", iban), iban, new Iban(IsoCountry.fromCode(countryCode), bban));
        }
    }

    @Test
    public void ibanValidationIsNotCaseSensitive() {
        for (String iban : VALID_IBANS) {
            String lowerCaseIban = iban.toLowerCase();
            assertTrue(String.format("%s should be valid", lowerCaseIban), Iban.isValid(lowerCaseIban));
        }
    }

    @Test
    public void ibanCreationIsNotCaseSensitive() {
        for (String iban : VALID_IBANS) {
            new Iban(iban.toLowerCase());
        }
    }

    @Test
    public void ibanFromBbanCreationIsNotCaseSensitive() {
        for (String iban : VALID_IBANS) {
            String countryCode = iban.substring(0, 2);
            String bban = iban.substring(4);
            assertEquals(String.format("%s not properly calculated", iban), iban, new Iban(IsoCountry.fromCode(countryCode), bban.toLowerCase()).toString());
        }
    }

    @Test
    public void printableIbansAreValid() {
        Iban iban = new Iban(VALID_IBAN);
        String printableIban = iban.toPrintableString();

        assertTrue(Iban.isValid(printableIban));
        assertEquals(iban, new Iban(printableIban));
    }

    @Test
    public void equalityTest() {
        Iban iban1 = new Iban(VALID_IBAN);
        Iban iban2 = new Iban(iban1.toPrintableString());
        Iban iban3 = new Iban(VALID_IBAN.toLowerCase());

        assertTrue(iban1.equals(iban1));
        assertTrue(iban2.equals(iban2));
        assertTrue(iban3.equals(iban3));

        assertTrue(iban1.equals(iban2));
        assertTrue(iban2.equals(iban1));
        assertTrue(iban2.equals(iban3));
        assertTrue(iban3.equals(iban2));
        assertTrue(iban1.equals(iban3));
        assertTrue(iban3.equals(iban1));
        assertTrue(iban1.hashCode() == iban2.hashCode());
        assertTrue(iban2.hashCode() == iban3.hashCode());

        assertFalse(iban1.equals(null));
        assertFalse(iban1.equals(new Object()));
        assertFalse(iban1.equals(new Iban(VALID_IBAN2)));
    }

}
