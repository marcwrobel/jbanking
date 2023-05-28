package fr.marcwrobel.jbanking.iban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.IsoCurrency;
import java.util.Random;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RandomIbanTest {

  @Test
  void whenRandomIsNull_thenThrows() {
    assertThrows(NullPointerException.class, () -> new RandomIban(null));
  }

  @Test
  void whenRandomIsKnown_thenResultIsDeterministic() {
    RandomIban random = new RandomIban(new Random(0));
    Iban iban = random.next();
    assertEquals(new Iban("GB93SXVN31194773254451"), iban);
  }

  @Test
  void generatedIbansAreValid() {
    RandomIban random = new RandomIban();

    for (int i = 0; i < BbanStructure.values().length * 10000; i++) {
      Iban iban = random.next();
      assertTrue(Iban.isValid(iban.toString()));
    }
  }

  @Nested
  class ByStructure {

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(NullPointerException.class, () -> random.next(new BbanStructure[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(IllegalArgumentException.class, () -> random.next(new BbanStructure[] {}));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomIban random = new RandomIban(new Random(0));
      Iban iban = random.next(BbanStructure.FR, BbanStructure.DE);
      assertEquals(new Iban("DE51897531194773254451"), iban);
    }

    @Test
    void whenStructureIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();
      Iban iban = random.next(BbanStructure.FR);
      assertEquals(IsoCountry.FR, iban.getCountry());
    }
  }

  @Nested
  class ByCountry {

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(IllegalArgumentException.class, () -> random.next(new IsoCountry[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(IllegalArgumentException.class, () -> random.next(new IsoCountry[] {}));
    }

    @Test
    void whenCountryDoesNotHaveACorrespondingStructure_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(IllegalArgumentException.class, () -> random.next(IsoCountry.US));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomIban random = new RandomIban(new Random(0));
      Iban iban = random.next(IsoCountry.ES, IsoCountry.HU);
      assertEquals(new Iban("HU95897531194773254451038472"), iban);
    }

    @Test
    void whenCountryIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();
      Iban iban = random.next(IsoCountry.FR);
      assertEquals(IsoCountry.FR, iban.getCountry());
    }
  }

  @Nested
  class ByCountryCode {

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(IllegalArgumentException.class, () -> random.next(new String[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(IllegalArgumentException.class, () -> random.next(new String[] {}));
    }

    @Test
    void whenCountryDoesNotHaveACorrespondingStructure_thenThrows() {
      RandomIban random = new RandomIban();
      String code = IsoCountry.JP.getAlpha2Code();
      assertThrows(IllegalArgumentException.class, () -> random.next(code));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomIban random = new RandomIban(new Random(0));
      Iban iban = random.next(IsoCountry.BE.getAlpha2Code(), IsoCountry.IS.getAlpha2Code());
      assertEquals(new Iban("IS958975311947732544510384"), iban);
    }

    @Test
    void whenCountryIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();
      Iban iban = random.next(IsoCountry.NL.getAlpha2Code());
      assertEquals(IsoCountry.NL, iban.getCountry());
    }
  }

  @Nested
  class ByCurrency {

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(NullPointerException.class, () -> random.next(new IsoCurrency[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomIban random = new RandomIban();
      assertThrows(IllegalArgumentException.class, () -> random.next(new IsoCurrency[] {}));
    }

    @Test
    void whenChosenCurrencyCountryDoesNotHaveACorrespondingStructure_thenThrows() {
      RandomIban random = new RandomIban();
      System.out.println(random.next(IsoCurrency.USD));
      assertThrows(IllegalArgumentException.class, () -> random.next(IsoCurrency.CNY));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomIban random = new RandomIban(new Random(0));
      Iban iban = random.next(IsoCurrency.GBP, IsoCurrency.EUR);
      assertEquals(new Iban("AD1289753119KHLR8RY8RZMZ"), iban);
    }

    @Test
    void whenCountryIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();
      Iban iban = random.next(IsoCurrency.GBP);
      assertEquals(IsoCountry.GB, iban.getCountry());
    }
  }
}
