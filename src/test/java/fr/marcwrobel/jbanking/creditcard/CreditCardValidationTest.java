package fr.marcwrobel.jbanking.creditcard;

import static fr.marcwrobel.jbanking.creditcard.CreditCard.CreditCardType.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardValidationTest {

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
  @DisplayName("Check valid visa card number")
  void validVisaCardNumber() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checkCreditCardNumber(VALID_VISA);

    assertTrue(result);
  }

  @Test
  @DisplayName("Check invalid visa card number")
  void invalidVisaCardNumber() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checkCreditCardNumber(INVALID_VISA);

    assertFalse(result);
  }

  @Test
  @DisplayName("Check checksum for valid visa card number")
  void validVisaCardNumberChecksum() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checksumValidation(VALID_VISA);

    assertTrue(result);
  }

  @Test
  @DisplayName("Check checksum for invalid visa card number")
  void invalidVisaCardNumberChecksum() {
    CreditCardValidation validation = new CreditCardValidation();
    final boolean result = validation.checksumValidation(INVALID_VISA);

    assertFalse(result);
  }


}
