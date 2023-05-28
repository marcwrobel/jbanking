package fr.marcwrobel.jbanking.bic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.IsoCurrency;
import java.util.Random;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RandomBicTest {

  @Test
  void whenRandomIsNull_thenThrows() {
    assertThrows(NullPointerException.class, () -> new RandomBic(null));
  }

  @Test
  void whenRandomIsKnown_thenResultIsDeterministic() {
    RandomBic random = new RandomBic(new Random(0));
    Bic bic = random.next();
    assertEquals(new Bic("SXVNKW39VPC"), bic);
  }

  @Test
  void generatedBicsAreValid() {
    RandomBic random = new RandomBic();

    for (int i = 0; i < IsoCountry.values().length * 10000; i++) {
      Bic bic = random.next();
      assertTrue(Bic.isValid(bic.toString()));
    }
  }

  @Nested
  class ByCountry {

    @Test
    void whenArrayIsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(NullPointerException.class, () -> random.next((IsoCountry[]) null));
    }

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(NullPointerException.class, () -> random.next(new IsoCountry[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(IllegalArgumentException.class, () -> random.next(new IsoCountry[] {}));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomBic random = new RandomBic(new Random(0));
      Bic bic = random.next(IsoCountry.FR, IsoCountry.DE);
      assertEquals(new Bic("SXVNDE39VPC"), bic);
    }

    @Test
    void whenCountryIsKnown_thenSameBicCountry() {
      RandomBic random = new RandomBic();
      Bic bic = random.next(IsoCountry.FR);
      assertEquals(IsoCountry.FR, bic.getCountry());
    }
  }

  @Nested
  class ByCountryCode {

    @Test
    void whenArrayIsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(NullPointerException.class, () -> random.next((String[]) null));
    }

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(IllegalArgumentException.class, () -> random.next(new String[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(IllegalArgumentException.class, () -> random.next(new String[] {}));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomBic random = new RandomBic(new Random(0));
      Bic bic = random.next(IsoCountry.JP.getAlpha2Code(), IsoCountry.US.getAlpha2Code());
      assertEquals(new Bic("SXVNUS39VPC"), bic);
    }

    @Test
    void whenCountryIsKnown_thenSameBicCountry() {
      RandomBic random = new RandomBic();
      Bic bic = random.next(IsoCountry.GB.getAlpha2Code());
      assertEquals(IsoCountry.GB, bic.getCountry());
    }
  }

  @Nested
  class ByCurrency {

    @Test
    void whenArrayIsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(NullPointerException.class, () -> random.next((IsoCurrency[]) null));
    }

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(NullPointerException.class, () -> random.next(new IsoCurrency[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomBic random = new RandomBic();
      assertThrows(IllegalArgumentException.class, () -> random.next(new IsoCurrency[] {}));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomBic random = new RandomBic(new Random(0));
      Bic bic = random.next(IsoCurrency.USD, IsoCurrency.EUR);
      assertEquals(new Bic("SXVNAD39VPC"), bic);
    }

    @Test
    void whenCountryIsKnown_thenSameBicCountry() {
      RandomBic random = new RandomBic();
      Bic bic = random.next(IsoCurrency.JPY);
      assertEquals(IsoCountry.JP, bic.getCountry());
    }
  }
}
