package fr.marcwrobel.jbanking.creditcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static fr.marcwrobel.jbanking.creditcard.CreditCard.CreditCardType.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreditCardTest {

  @Test
  @DisplayName("Return valid card type (MASTERCARD)")
  void returnsTypeMaster() {
    CreditCard card = new CreditCard(MASTERCARD, "5412345678912345");
    assertEquals("Mastercard", card.getType()) ;
  }

  @Test
  @DisplayName("Return valid card type (VISA)")
  void returnsTypeVisa() {
    CreditCard card = new CreditCard(VISA, "5412345678912345");
    assertEquals("Visa", card.getType()) ;
  }

  @Test
  @DisplayName("return invalid card type")
  void doesntReturnUnsupportedCardType() {
    CreditCard card = new CreditCard(DISCOVER, "4102938475610");
    assertEquals("Unsupported Card Type", card.getType());
  }

  @Test
  @DisplayName("Return card number")
  void returnsNumber() {
    CreditCard card = new CreditCard(MASTERCARD, "5412345678912345");
    assertEquals("5412345678912345", card.getCardnumber());
  }
}
