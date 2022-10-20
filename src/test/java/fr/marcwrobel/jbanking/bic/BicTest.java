package fr.marcwrobel.jbanking.bic;

import static fr.marcwrobel.jbanking.internal.TestUtils.BLANK;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.marcwrobel.jbanking.IsoCountry;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link Bic} class.
 */
class BicTest {

  private static final String INSTITUTION_CODE = "BNPA";
  private static final IsoCountry COUNTRY_CODE = IsoCountry.FR;
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
  void nullIsNotValid() {
    assertFalse(Bic.isValid(null));
  }

  @Test
  void aBicCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> new Bic(null));
  }

  @Test
  void aBicCannotBeBlank() {
    assertThrows(BicFormatException.class, () -> new Bic(BLANK));
  }

  @Test
  void blankIsNotValid() {
    assertFalse(Bic.isValid(BLANK));
  }

  @Test
  void aBicMustBeProperlyFormatted() {
    BicFormatException e = assertThrows(BicFormatException.class, () -> new Bic(BIC_WITH_INVALID_FORMAT));
    assertEquals(BIC_WITH_INVALID_FORMAT, e.getInputString());
    assertTrue(e.getMessage().contains("format"));
  }

  @Test
  void aStringWithInvalidFormatIsNotValid() {
    assertFalse(Bic.isValid(BIC_WITH_INVALID_FORMAT));
  }

  @Test
  void aBicCountryCodeMustBeKnown() {
    BicFormatException e = assertThrows(BicFormatException.class, () -> new Bic(BIC_WITH_INVALID_COUNTRY_CODE));
    assertEquals(BIC_WITH_INVALID_COUNTRY_CODE, e.getInputString());
    assertTrue(e.getMessage().contains("country"));
  }

  @Test
  void aStringWithInvalidCountryCodeIsNotValid() {
    assertFalse(Bic.isValid(BIC_WITH_INVALID_COUNTRY_CODE));
  }

  @Test
  void validBic8AreAllowed() {
    assertDoesNotThrow(() -> new Bic(VALID_BIC8));
  }

  @Test
  void aValidBic8IsValid() {
    assertTrue(Bic.isValid(VALID_BIC8));
  }

  @Test
  void validBic11AreAllowed() {
    assertDoesNotThrow(() -> new Bic(VALID_BIC11));
  }

  @Test
  void aValidBic11IsValid() {
    assertTrue(Bic.isValid(VALID_BIC11));
  }

  @Test
  void bicIsCaseInsensitive() {
    assertDoesNotThrow(() -> new Bic(VALID_BIC11_LOWERCASE));
  }

  @Test
  void bicDecompositionTest() {
    Bic bic = new Bic(VALID_BIC8);
    assertEquals(INSTITUTION_CODE, bic.getInstitutionCode());
    assertEquals(COUNTRY_CODE, bic.getCountry());
    assertEquals(COUNTRY_CODE.getAlpha2Code(), bic.getCountryCode());
    assertEquals(LOCATION_CODE, bic.getLocationCode());
    assertEquals(BRANCH_CODE, bic.getBranchCode());
  }

  @Test
  void liveBicTest() {
    Bic liveBic = new Bic(VALID_BIC11);
    assertTrue(liveBic.isLiveBic());
    assertFalse(liveBic.isTestBic());

    Bic testBic = liveBic.asTestBic();
    assertTrue(testBic.isTestBic());
    assertEquals(new Bic(VALID_BIC11_TEST), testBic);
  }

  @Test
  void testBicTest() {
    Bic testBic = new Bic(VALID_BIC11_TEST);
    assertFalse(testBic.isLiveBic());
    assertTrue(testBic.isTestBic());
    assertEquals(new Bic(VALID_BIC11_TEST), testBic.asTestBic());
    assertSame(testBic.asTestBic(), testBic.asTestBic());
  }

  @Test
  void testBicTransformationTest() {
    Bic liveBic = new Bic(VALID_BIC11);
    Bic testBic = new Bic(VALID_BIC11_TEST);
    Bic liveBicAsTestBic = liveBic.asTestBic();

    assertTrue(testBic.isTestBic());
    assertEquals(testBic, liveBicAsTestBic);
  }

  @Test
  void equalityTest() {
    Bic bic1 = new Bic(VALID_BIC8);
    Bic bic2 = new Bic(VALID_BIC11);
    Bic bic3 = new Bic(VALID_BIC8_LOWERCASE);

    assertEquals(bic1, bic1);
    assertEquals(bic2, bic2);
    assertEquals(bic3, bic3);

    assertEquals(bic1, bic2);
    assertEquals(bic2, bic1);
    assertEquals(bic2, bic3);
    assertEquals(bic3, bic2);
    assertEquals(bic1, bic3);
    assertEquals(bic3, bic1);
    assertEquals(bic1.hashCode(), bic2.hashCode());
    assertEquals(bic2.hashCode(), bic3.hashCode());

    assertNotEquals(null, bic1);
    assertNotEquals(bic1, new Object());
  }

  @Test
  void toStringReturnsANormalizedBic() {
    Bic bic = new Bic(VALID_BIC8_LOWERCASE);
    assertEquals(VALID_BIC11, bic.toString());
  }
}
