package fr.marcwrobel.jbanking.creditcard;

import static fr.marcwrobel.jbanking.creditcard.CreditCard.CreditCardType.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardValidationTest {

  private static final String VALID_MASTERCARD = "5271081850767485";

  private static final String INVALID = "1312345678912345";

  private static final String VALID_VISA = "4779443429717849";

  @Test
  @DisplayName("Check valid mastercard number")
  void validMastercardNumber() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checkCreditCardNumber(VALID_MASTERCARD);

    assertTrue(result);
  }

  @Test
  @DisplayName("Check valid visa card number")
  void validVisaCardNumber() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checkCreditCardNumber(VALID_VISA);

    assertTrue(result);
  }

  @Test
  @DisplayName("Check invalid credit card number")
  void invalidCardNumber() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checkCreditCardNumber(INVALID);

    assertFalse(result);
  }

  @Test
  @DisplayName("Check checksum for valid mastercard number")
  void validMastercardNumberChecksum() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checksumValidation(VALID_MASTERCARD);

    assertTrue(result);
  }

  @Test
  @DisplayName("Check checksum for valid visa card number")
  void validVisaCardNumberChecksum() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checksumValidation(VALID_VISA);

    assertTrue(result);
  }

  @Test
  @DisplayName("Check checksum for invalid credit card number")
  void invalidCardNumberChecksum() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checksumValidation(INVALID);

    assertFalse(result);
  }

  @Test
  @DisplayName("Check valid mastercard")
  void validMastercardCheck() {
    CreditCardValidation validation = new CreditCardValidation();
    CreditCard validMastercard = new CreditCard(MASTERCARD, VALID_MASTERCARD);
    final String result = validation.check(validMastercard);

    assertEquals("Mastercard", result);
  }

  @Test
  @DisplayName("Check valid visa")
  void validVisaCheck() {
    CreditCardValidation validation = new CreditCardValidation();
    CreditCard validVisa = new CreditCard(VISA, VALID_VISA);
    final String result = validation.check(validVisa);

    assertEquals("Visa", result);
  }

  @Test
  @DisplayName("Check invalid credit card")
  void invalidCardCheck() {
    CreditCardValidation validation = new CreditCardValidation();
    CreditCard invalidCard = new CreditCard(MASTERCARD, INVALID);
    final String result = validation.check(invalidCard);

    assertEquals("Not a valid credit card", result);
  }
}
