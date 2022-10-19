package fr.marcwrobel.jbanking.iban;

import static org.junit.jupiter.api.Assertions.*;

import fr.marcwrobel.jbanking.IsoCountry;
import java.util.Random;
import org.junit.jupiter.api.Test;

class RandomIbanTest {

  @Test
  void randomMustBeNonNull() {
    assertThrows(NullPointerException.class, () -> new RandomIban(null));
  }

  @Test
  void bbanStructureArrayMustNotContainNull() {
    RandomIban random = new RandomIban();
    assertThrows(NullPointerException.class, () -> random.next(new BbanStructure[] { null }));
  }

  @Test
  void bbanStructureArrayMustNotBeEmpty() {
    RandomIban random = new RandomIban();
    assertThrows(IllegalArgumentException.class, () -> random.next(new BbanStructure[] {}));
  }

  @Test
  void randomIbanIsDeterministic() {
    RandomIban random = new RandomIban(new Random(0));

    assertEquals(new Iban("GB93SXVN31194773254451"), random.next());
  }

  @Test
  void expectedRandomIbanCountry() {
    IsoCountry country = IsoCountry.FR;
    RandomIban random = new RandomIban();

    Iban iban = random.next(BbanStructure.valueOf(country.name()));

    assertEquals(country, iban.getCountry());
  }

  @Test
  void randomIbansAreValid() {
    RandomIban random = new RandomIban();

    for (int i = 0; i < BbanStructure.values().length * 10000; i++) {
      Iban iban = assertDoesNotThrow(() -> random.next());
      assertTrue(Iban.isValid(iban.toString()));
    }
  }
}
