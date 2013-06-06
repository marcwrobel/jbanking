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
package org.jbanking.iso.bic;

import org.jbanking.TestUtils;
import org.jbanking.iso.IsoCountry;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the {@link org.jbanking.iso.bic.Bic} class.
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


    @Test(expected = IllegalArgumentException.class)
    public void aBicCannotBeNull() {
        new Bic(null);
    }

    @Test(expected = BicFormatException.class)
    public void aBicCannotBeBlank() {
        new Bic(TestUtils.BLANK);
    }

    @Test(expected = BicFormatException.class)
    public void aBicMustBeProperlyFormatted() {
        try {
            new Bic(BIC_WITH_INVALID_FORMAT);
        } catch (BicFormatException e) {
            Assert.assertEquals(BIC_WITH_INVALID_FORMAT, e.getInputString());
            Assert.assertTrue(e.getMessage().contains("format"));
            throw e;
        }
    }

    @Test(expected = BicFormatException.class)
    public void aBicCountryCodeMustBeKnown() {
        try {
            new Bic(BIC_WITH_INVALID_COUNTRY_CODE);
        } catch (BicFormatException e) {
            Assert.assertEquals(BIC_WITH_INVALID_COUNTRY_CODE, e.getInputString());
            Assert.assertTrue(e.getMessage().contains("country"));
            throw e;
        }
    }

    @Test
    public void validBic8AreAllowed() {
        new Bic(VALID_BIC8);
    }

    @Test
    public void validBic11AreAllowed() {
        new Bic(VALID_BIC11);
    }

    @Test
    public void bicIsCaseInsensitive() {
        new Bic(VALID_BIC11_LOWERCASE);
    }

    @Test
    public void bicDecompositionTest() {
        Bic bic = new Bic(VALID_BIC8);
        Assert.assertEquals(INSTITUTION_CODE, bic.getInstitutionCode());
        Assert.assertEquals(COUNTRY_CODE, bic.getCountryCode());
        Assert.assertEquals(LOCATION_CODE, bic.getLocationCode());
        Assert.assertEquals(BRANCH_CODE, bic.getBranchCode());
    }

    @Test
    public void testBicTest() {
        Assert.assertFalse(new Bic(VALID_BIC11).isTestBic());
        Assert.assertTrue(new Bic(VALID_BIC11).isLiveBic());

        Assert.assertFalse(new Bic(VALID_BIC11_TEST).isLiveBic());
        Assert.assertTrue(new Bic(VALID_BIC11_TEST).isTestBic());
    }

    @Test
    public void testBicTransformationTest() {
        Bic liveBic = new Bic(VALID_BIC11);
        Bic testBic = new Bic(VALID_BIC11_TEST);
        Bic liveBicAsTestBic = liveBic.asTestBic();

        Assert.assertTrue(testBic.isTestBic());
        Assert.assertEquals(testBic, liveBicAsTestBic);
    }

    @Test
    public void equalityTest() {
        Bic bic1 = new Bic(VALID_BIC8);
        Bic bic2 = new Bic(VALID_BIC11);
        Bic bic3 = new Bic(VALID_BIC8_LOWERCASE);

        Assert.assertTrue(bic1.equals(bic1));
        Assert.assertTrue(bic2.equals(bic2));
        Assert.assertTrue(bic3.equals(bic3));

        Assert.assertTrue(bic1.equals(bic2));
        Assert.assertTrue(bic2.equals(bic1));
        Assert.assertTrue(bic2.equals(bic3));
        Assert.assertTrue(bic3.equals(bic2));
        Assert.assertTrue(bic1.equals(bic3));
        Assert.assertTrue(bic3.equals(bic1));
        Assert.assertTrue(bic1.hashCode() == bic2.hashCode());
        Assert.assertTrue(bic2.hashCode() == bic3.hashCode());

        Assert.assertFalse(bic1.equals(null));
        Assert.assertFalse(bic2.equals(null));
        Assert.assertFalse(bic3.equals(null));
    }

    @Test
    public void toStringReturnsANormalizedBic() {
        Bic bic = new Bic(VALID_BIC8_LOWERCASE);
        Assert.assertEquals(VALID_BIC11, bic.toString());
    }
}
