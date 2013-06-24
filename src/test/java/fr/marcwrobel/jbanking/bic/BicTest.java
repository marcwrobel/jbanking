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
import fr.marcwrobel.jbanking.TestUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link Bic} class.
 *
 * @author Marc Wrobel
 */
public class BicTest {

    private static final String INSTITUTION_CODE = "BNPA";
    private static final String COUNTRY_CODE = IsoCountry.FRANCE.getCode();
    private static final String LOCATION_CODE = "PP";
    private static final String LOCATION_CODE_TEST = "P0";
    private static final String BRANCH_CODE = Bic.PRIMARY_OFFICE_BRANCH_CODE;

    private static final String BIC_WITH_INVALID_FORMAT = INSTITUTION_CODE + COUNTRY_CODE;
    private static final String BIC_WITH_INVALID_COUNTRY_CODE = INSTITUTION_CODE + "OO" + LOCATION_CODE;
    private static final String VALID_BIC8 = INSTITUTION_CODE + COUNTRY_CODE + LOCATION_CODE;
    private static final String VALID_BIC11 = INSTITUTION_CODE + COUNTRY_CODE + LOCATION_CODE + BRANCH_CODE;
    private static final String VALID_BIC11_TEST = INSTITUTION_CODE + COUNTRY_CODE + LOCATION_CODE_TEST + BRANCH_CODE;
    private static final String VALID_BIC8_LOWERCASE = VALID_BIC8.toLowerCase();
    private static final String VALID_BIC11_LOWERCASE = VALID_BIC11.toLowerCase();

    @Test
    public void nullIsNotValid() {
        assertFalse(Bic.isValid(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void aBicCannotBeNull() {
        new Bic(null);
    }

    @Test(expected = BicFormatException.class)
    public void aBicCannotBeBlank() {
        new Bic(TestUtils.BLANK);
    }

    @Test
    public void blankIsNotValid() {
        assertFalse(Bic.isValid(TestUtils.BLANK));
    }

    @Test(expected = BicFormatException.class)
    public void aBicMustBeProperlyFormatted() {
        try {
            new Bic(BIC_WITH_INVALID_FORMAT);
        } catch (BicFormatException e) {
            assertEquals(BIC_WITH_INVALID_FORMAT, e.getInputString());
            assertTrue(e.getMessage().contains("format"));
            throw e;
        }
    }

    @Test
    public void aStringWithInvalidFormatIsNotValid() {
        assertFalse(Bic.isValid(BIC_WITH_INVALID_FORMAT));
    }

    @Test(expected = BicFormatException.class)
    public void aBicCountryCodeMustBeKnown() {
        try {
            new Bic(BIC_WITH_INVALID_COUNTRY_CODE);
        } catch (BicFormatException e) {
            assertEquals(BIC_WITH_INVALID_COUNTRY_CODE, e.getInputString());
            assertTrue(e.getMessage().contains("country"));
            throw e;
        }
    }

    @Test
    public void aStringWithInvalidCountryCodeIsNotValid() {
        assertFalse(Bic.isValid(BIC_WITH_INVALID_COUNTRY_CODE));
    }

    @Test
    public void validBic8AreAllowed() {
        new Bic(VALID_BIC8);
    }

    @Test
    public void aValidBic8IsValid() {
        assertTrue(Bic.isValid(VALID_BIC8));
    }

    @Test
    public void validBic11AreAllowed() {
        new Bic(VALID_BIC11);
    }

    @Test
    public void aValidBic11IsValid() {
        assertTrue(Bic.isValid(VALID_BIC11));
    }

    @Test
    public void bicIsCaseInsensitive() {
        new Bic(VALID_BIC11_LOWERCASE);
    }

    @Test
    public void bicDecompositionTest() {
        Bic bic = new Bic(VALID_BIC8);
        assertEquals(INSTITUTION_CODE, bic.getInstitutionCode());
        assertEquals(COUNTRY_CODE, bic.getCountryCode());
        assertEquals(LOCATION_CODE, bic.getLocationCode());
        assertEquals(BRANCH_CODE, bic.getBranchCode());
    }

    @Test
    public void liveBicTest() {
        Bic liveBic = new Bic(VALID_BIC11);
        assertTrue(liveBic.isLiveBic());
        assertFalse(liveBic.isTestBic());

        Bic testBic = liveBic.asTestBic();
        assertTrue(testBic.isTestBic());
        assertEquals(new Bic(VALID_BIC11_TEST), testBic);
    }

    @Test
    public void testBicTest() {
        Bic testBic = new Bic(VALID_BIC11_TEST);
        assertFalse(testBic.isLiveBic());
        assertTrue(testBic.isTestBic());
        assertEquals(new Bic(VALID_BIC11_TEST), testBic.asTestBic());
        assertTrue(testBic.asTestBic() == testBic.asTestBic());
    }

    @Test
    public void testBicTransformationTest() {
        Bic liveBic = new Bic(VALID_BIC11);
        Bic testBic = new Bic(VALID_BIC11_TEST);
        Bic liveBicAsTestBic = liveBic.asTestBic();

        assertTrue(testBic.isTestBic());
        assertEquals(testBic, liveBicAsTestBic);
    }

    @Test
    public void equalityTest() {
        Bic bic1 = new Bic(VALID_BIC8);
        Bic bic2 = new Bic(VALID_BIC11);
        Bic bic3 = new Bic(VALID_BIC8_LOWERCASE);

        assertTrue(bic1.equals(bic1));
        assertTrue(bic2.equals(bic2));
        assertTrue(bic3.equals(bic3));

        assertTrue(bic1.equals(bic2));
        assertTrue(bic2.equals(bic1));
        assertTrue(bic2.equals(bic3));
        assertTrue(bic3.equals(bic2));
        assertTrue(bic1.equals(bic3));
        assertTrue(bic3.equals(bic1));
        assertTrue(bic1.hashCode() == bic2.hashCode());
        assertTrue(bic2.hashCode() == bic3.hashCode());

        assertFalse(bic1.equals(null));
        assertFalse(bic1.equals(new Object()));
    }

    @Test
    public void toStringReturnsANormalizedBic() {
        Bic bic = new Bic(VALID_BIC8_LOWERCASE);
        assertEquals(VALID_BIC11, bic.toString());
    }
}
