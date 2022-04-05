package fr.marcwrobel.jbanking.creditcard;

public class CreditCard {

  String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
    "(?<mastercard>5[1-5][0-9]{14})";

  public boolean checkCreditCardNumber(){
    return true;
  }

}
