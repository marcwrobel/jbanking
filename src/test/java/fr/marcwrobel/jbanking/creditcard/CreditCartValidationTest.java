package fr.marcwrobel.jbanking.creditcard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCartValidationTest {

  private static final String VALID_MASTERCARD = "5271081850767485";

  private static final String INVALID_MASTERCARD = "1312345678912345";

  private static final String VALID_VISA = "4779443429717849";

  private static final String INVALID_VISA = "0779443429717849";

  @Test
  @DisplayName("Check valid credit card number")
  void validCardNumber() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checkCreditCardNumber(VALID_MASTERCARD);

    assertTrue(result);
  }

  @Test
  @DisplayName("Check invalid credit card number")
  void invalidCardNumber() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checkCreditCardNumber(INVALID_MASTERCARD);

    assertFalse(result);
  }

  @Test
  @DisplayName("Check checksum for valid credit card number")
  void validCardNumberChecksum() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checksumValidation(VALID_MASTERCARD);

    assertTrue(result);
  }

  @Test
  @DisplayName("Check checksum for invalid credit card number")
  void invalidCardNumberChecksum() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checksumValidation(INVALID_MASTERCARD);

    assertFalse(result);
  }

  @Test
  @DisplayName("Check valid mastercard")
  void validMastercardCheck() {
    CreditCardValidation validation = new CreditCardValidation();
    CreditCard validMastercard = new CreditCard(CreditCard.CreditCardType.MASTERCARD, VALID_MASTERCARD);
    final String result = validation.check(validMastercard);

    assertEquals("Mastercard", result);
  }

  @Test
  @DisplayName("Check invalid mastercard")
  void invalidMastercardCheck() {
    CreditCardValidation validation = new CreditCardValidation();
    CreditCard invalidMastercard = new CreditCard(CreditCard.CreditCardType.MASTERCARD, INVALID_MASTERCARD);
    final String result = validation.check(invalidMastercard);

    assertEquals("Not a valid credit card", result);
  }

  @Test
  @DisplayName("Check valid visa")
  void validVisaCheck() {
    CreditCardValidation validation = new CreditCardValidation();
    CreditCard validVisa = new CreditCard(CreditCard.CreditCardType.VISA, VALID_VISA);
    final String result = validation.check(validVisa);

    assertEquals("Visa", result);
  }

  @Test
  @DisplayName("Check invalid mastercard")
  void invalidVisaCheck() {
    CreditCardValidation validation = new CreditCardValidation();
    CreditCard invalidVisa= new CreditCard(CreditCard.CreditCardType.MASTERCARD, INVALID_VISA);
    final String result = validation.check(invalidVisa);

    assertEquals("Not a valid credit card", result);
  }
}
