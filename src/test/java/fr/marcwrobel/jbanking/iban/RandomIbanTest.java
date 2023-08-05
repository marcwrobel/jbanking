package fr.marcwrobel.jbanking.iban;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.IsoCurrency;
import java.util.Random;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RandomIbanTest {

  @Test
  void whenRandomIsNull_thenThrows() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new RandomIban(null));
  }

  @Test
  void whenRandomIsKnown_thenResultIsDeterministic() {
    RandomIban random = new RandomIban(new Random(0));
    Iban iban = random.next();
    assertThat(iban).isEqualTo(new Iban("GB93SXVN31194773254451"));
  }

  @Test
  void generatedIbansAreValid() {
    RandomIban random = new RandomIban();

    for (int i = 0; i < BbanStructure.values().length * 10000; i++) {
      Iban iban = random.next();
      assertThat(Iban.isValid(iban.toString())).isTrue();
    }
  }

  @Nested
  class ByStructure {

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> random.next(new BbanStructure[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new BbanStructure[] {}));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomIban random = new RandomIban(new Random(0));
      Iban iban = random.next(BbanStructure.FR, BbanStructure.DE);
      assertThat(iban).isEqualTo(new Iban("DE51897531194773254451"));
    }

    @Test
    void whenStructureIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();
      Iban iban = random.next(BbanStructure.FR);
      assertThat(iban.getCountry()).isEqualTo(IsoCountry.FR);
    }
  }

  @Nested
  class ByCountry {

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new IsoCountry[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new IsoCountry[] {}));
    }

    @Test
    void whenCountryDoesNotHaveACorrespondingStructure_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(IsoCountry.US));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomIban random = new RandomIban(new Random(0));
      Iban iban = random.next(IsoCountry.ES, IsoCountry.HU);
      assertThat(iban).isEqualTo(new Iban("HU95897531194773254451038472"));
    }

    @Test
    void whenCountryIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();
      Iban iban = random.next(IsoCountry.FR);
      assertThat(iban.getCountry()).isEqualTo(IsoCountry.FR);
    }
  }

  @Nested
  class ByCountryCode {

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new String[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new String[] {}));
    }

    @Test
    void whenCountryDoesNotHaveACorrespondingStructure_thenThrows() {
      RandomIban random = new RandomIban();
      String code = IsoCountry.JP.getAlpha2Code();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(code));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomIban random = new RandomIban(new Random(0));
      Iban iban = random.next(IsoCountry.BE.getAlpha2Code(), IsoCountry.IS.getAlpha2Code());
      assertThat(iban).isEqualTo(new Iban("IS958975311947732544510384"));
    }

    @Test
    void whenCountryIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();
      Iban iban = random.next(IsoCountry.NL.getAlpha2Code());
      assertThat(iban.getCountry()).isEqualTo(IsoCountry.NL);
    }
  }

  @Nested
  class ByCurrency {

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> random.next(new IsoCurrency[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomIban random = new RandomIban();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new IsoCurrency[] {}));
    }

    @Test
    void whenChosenCurrencyCountryDoesNotHaveACorrespondingStructure_thenThrows() {
      RandomIban random = new RandomIban();
      System.out.println(random.next(IsoCurrency.USD));
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(IsoCurrency.CNY));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomIban random = new RandomIban(new Random(0));
      Iban iban = random.next(IsoCurrency.GBP, IsoCurrency.EUR);
      assertThat(iban).isEqualTo(new Iban("AD1289753119KHLR8RY8RZMZ"));
    }

    @Test
    void whenCountryIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();
      Iban iban = random.next(IsoCurrency.GBP);
      assertThat(iban.getCountry()).isEqualTo(IsoCountry.GB);
    }
  }
}
