package fr.marcwrobel.jbanking.creditcard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardValidation {

  private static final String REGEX = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
    "(?<mastercard>5[1-5][0-9]{14}))";

  public boolean checkCreditCardNumber(String cardNumber){
    Pattern pattern = Pattern.compile(REGEX);
    Matcher matcher = pattern.matcher(cardNumber);

    if(matcher.matches()) {
      return true;
    }
    return false;
  }

  /**
   * the following code was taken from https://howtodoinjava.com/java/regex/java-regex-validate-credit-card-numbers/
   * to build a checksum with the card number for further validation.
   * @param cardNumber credit card number to check
   * @return true, if checksum is valid, else false
   */
  public boolean checksumValidation(String cardNumber)
  {
    int sum = 0;
    boolean multiply = false;
    for (int i = cardNumber.length() - 1; i >= 0; i--)
    {
      int n = Character.getNumericValue(cardNumber.charAt(i));
      if (multiply)
      {
        n *= 2;
        if (n > 9)
        {
          n = (n % 10) + 1;
        }
      }
      sum += n;
      multiply = !multiply;
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
