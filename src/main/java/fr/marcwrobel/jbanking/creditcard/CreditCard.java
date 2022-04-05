package fr.marcwrobel.jbanking.creditcard;

public class CreditCard {
  private CreditCardType type;
  private String cardnumber;

  private enum CreditCardType {
    MASTERCARD,
    VISA
  }

  public CreditCard(CreditCardType type, String cardnumber) {
    this.cardnumber = cardnumber;
    this.type = type;
  }

  public String getType() {
    switch (type) {
      case MASTERCARD:
        return "Mastercard";
      case VISA:
        return "Visa";
      default:
        return "None";
    }
  }

  public String getCardnumber() {
    return this.cardnumber;
  }
}

