package fr.marcwrobel.jbanking.creditcard;

public class CreditCardValidation {

  private

  String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
    "(?<mastercard>5[1-5][0-9]{14})";

  public boolean checkCreditCardNumber(String cardNumber){
    return true;
  }

  public boolean checksumValidation(String cardNumber)
  {
    int sum = 0;
    boolean alternate = false;
    for (int i = cardNumber.length() - 1; i >= 0; i--)
    {
      int n = Integer.parseInt(cardNumber.substring(i, i + 1));
      if (alternate)
      {
        n *= 2;
        if (n > 9)
        {
          n = (n % 10) + 1;
        }
      }
      sum += n;
      alternate = !alternate;
    }
    return (sum % 10 == 0);
  }

  /**
   * returns the type of credit card if the card number is valid
   * @param card CreditCard
   * @return credit card type as string
   */

  public String check(CreditCard card) {
    String cn = card.getCardnumber();
    if (this.checksumValidation(cn) && this.checkCreditCardNumber(cn)) {
      return card.getType();
    }
    return "Not a valid credit card";
  }

}
