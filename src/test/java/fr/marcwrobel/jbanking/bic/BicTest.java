package fr.marcwrobel.jbanking.bic;

import static fr.marcwrobel.jbanking.TestUtils.BLANK;
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
  private static final String BIC_WITH_INVALID_COUNTRY_CODE =
      INSTITUTION_CODE + "OO" + LOCATION_CODE;
  private static final String VALID_BIC8 = INSTITUTION_CODE + COUNTRY_CODE + LOCATION_CODE;
  private static final String VALID_BIC11 =
      INSTITUTION_CODE + COUNTRY_CODE + LOCATION_CODE + BRANCH_CODE;
  private static final String VALID_BIC11_TEST =
      INSTITUTION_CODE + COUNTRY_CODE + LOCATION_CODE_TEST + BRANCH_CODE;
  private static final String VALID_BIC8_LOWERCASE = VALID_BIC8.toLowerCase();
  private static final String VALID_BIC11_LOWERCASE = VALID_BIC11.toLowerCase();

  @Test
  public void nullIsNotValid() {
    assertFalse(Bic.isValid(null));
  }

  @Test
  public void aBicCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> new Bic(null));
  }

  @Test
  public void aBicCannotBeBlank() {
    assertThrows(BicFormatException.class, () -> new Bic(BLANK));
  }

  @Test
  public void blankIsNotValid() {
    assertFalse(Bic.isValid(BLANK));
  }

  @Test
  public void aBicMustBeProperlyFormatted() {
    BicFormatException e =
        assertThrows(BicFormatException.class, () -> new Bic(BIC_WITH_INVALID_FORMAT));
    assertEquals(BIC_WITH_INVALID_FORMAT, e.getInputString());
    assertTrue(e.getMessage().contains("format"));
  }

  @Test
  public void aStringWithInvalidFormatIsNotValid() {
    assertFalse(Bic.isValid(BIC_WITH_INVALID_FORMAT));
  }

  @Test
  public void aBicCountryCodeMustBeKnown() {
    BicFormatException e =
        assertThrows(BicFormatException.class, () -> new Bic(BIC_WITH_INVALID_COUNTRY_CODE));
    assertEquals(BIC_WITH_INVALID_COUNTRY_CODE, e.getInputString());
    assertTrue(e.getMessage().contains("country"));
  }

  @Test
  public void aStringWithInvalidCountryCodeIsNotValid() {
    assertFalse(Bic.isValid(BIC_WITH_INVALID_COUNTRY_CODE));
  }

  @Test
  public void validBic8AreAllowed() {
    assertDoesNotThrow(() -> new Bic(VALID_BIC8));
  }

  @Test
  public void aValidBic8IsValid() {
    assertTrue(Bic.isValid(VALID_BIC8));
  }

  @Test
  public void validBic11AreAllowed() {
    assertDoesNotThrow(() -> new Bic(VALID_BIC11));
  }

  @Test
  public void aValidBic11IsValid() {
    assertTrue(Bic.isValid(VALID_BIC11));
  }

  @Test
  public void bicIsCaseInsensitive() {
    assertDoesNotThrow(() -> new Bic(VALID_BIC11_LOWERCASE));
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
    assertSame(testBic.asTestBic(), testBic.asTestBic());
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
  public void toStringReturnsANormalizedBic() {
    Bic bic = new Bic(VALID_BIC8_LOWERCASE);
    assertEquals(VALID_BIC11, bic.toString());
  }
}
