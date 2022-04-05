package fr.marcwrobel.jbanking.creditcard;

public class CreditCard {
    private CreditCardType type;
    private String cardNumber;

    public enum CreditCardType {
      MASTERCARD,
      VISA,
      DISCOVER,
      JCB
    }

    public CreditCard(CreditCardType type, String cardNumber) {
        this.cardNumber = cardNumber;
        this.type = type;
    }

  /**
   * getter for credit card type
   * @return String
   */

  public String getType() {
      switch (type) {
        case MASTERCARD:
          return "Mastercard";
        case VISA:
          return "Visa";
        default:
          return "Unsupported Card Type";
      }
    }

  /**
   * getter for credit card number
   * @return String
   */
  public String getCardnumber() {
      return this.cardNumber;
    }
}
