package fr.marcwrobel.jbanking.bic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import fr.marcwrobel.jbanking.IsoCountry;
import fr.marcwrobel.jbanking.IsoCurrency;
import java.util.Random;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RandomBicTest {

  @Test
  void whenRandomIsNull_thenThrows() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new RandomBic(null));
  }

  @Test
  void whenRandomIsKnown_thenResultIsDeterministic() {
    RandomBic random = new RandomBic(new Random(0));
    Bic bic = random.next();
    assertThat(bic).isEqualTo(new Bic("SXVNKW39VPC"));
  }

  @Test
  void generatedBicsAreValid() {
    RandomBic random = new RandomBic();

    for (int i = 0; i < IsoCountry.values().length * 10000; i++) {
      Bic bic = random.next();
      assertThat(Bic.isValid(bic.toString())).isTrue();
    }
  }

  @Nested
  class ByCountry {

    @Test
    void whenArrayIsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> random.next((IsoCountry[]) null));
    }

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> random.next(new IsoCountry[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new IsoCountry[] {}));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomBic random = new RandomBic(new Random(0));
      Bic bic = random.next(IsoCountry.FR, IsoCountry.DE);
      assertThat(bic).isEqualTo(new Bic("SXVNDE39VPC"));
    }

    @Test
    void whenCountryIsKnown_thenSameBicCountry() {
      RandomBic random = new RandomBic();
      Bic bic = random.next(IsoCountry.FR);
      assertThat(bic.getCountry()).isEqualTo(IsoCountry.FR);
    }
  }

  @Nested
  class ByCountryCode {

    @Test
    void whenArrayIsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> random.next((String[]) null));
    }

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new String[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new String[] {}));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomBic random = new RandomBic(new Random(0));
      Bic bic = random.next(IsoCountry.JP.getAlpha2Code(), IsoCountry.US.getAlpha2Code());
      assertThat(bic).isEqualTo(new Bic("SXVNUS39VPC"));
    }

    @Test
    void whenCountryIsKnown_thenSameBicCountry() {
      RandomBic random = new RandomBic();
      Bic bic = random.next(IsoCountry.GB.getAlpha2Code());
      assertThat(bic.getCountry()).isEqualTo(IsoCountry.GB);
    }
  }

  @Nested
  class ByCurrency {

    @Test
    void whenArrayIsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> random.next((IsoCurrency[]) null));
    }

    @Test
    void whenArrayContainsNull_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> random.next(new IsoCurrency[] { null }));
    }

    @Test
    void whenArrayIsEmpty_thenThrows() {
      RandomBic random = new RandomBic();
      assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> random.next(new IsoCurrency[] {}));
    }

    @Test
    void whenRandomIsKnown_thenResultIsDeterministic() {
      RandomBic random = new RandomBic(new Random(0));
      Bic bic = random.next(IsoCurrency.USD, IsoCurrency.EUR);
      assertThat(bic).isEqualTo(new Bic("SXVNAD39VPC"));
    }

    @Test
    void whenCountryIsKnown_thenSameBicCountry() {
      RandomBic random = new RandomBic();
      Bic bic = random.next(IsoCurrency.JPY);
      assertThat(bic.getCountry()).isEqualTo(IsoCountry.JP);
    }
  }
}
