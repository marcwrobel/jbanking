package fr.marcwrobel.jbanking.iban;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.marcwrobel.jbanking.IsoCountry;
import java.util.Random;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RandomIbanTest {

  @Test
  void whenRandomIsNull_thenThrows() {
    assertThrows(NullPointerException.class, () -> new RandomIban(null));
  }

  @Test
  void whenRandomIsKnownAndNextIsCalledWithoutArgument_thenResultIsDeterministic() {
    RandomIban random = new RandomIban(new Random(0));
    assertEquals(new Iban("GB93SXVN31194773254451"), random.next());
  }

  @Test
  void generatedIbansAreValid() {
    RandomIban random = new RandomIban();

    for (int i = 0; i < BbanStructure.values().length * 10000; i++) {
      Iban iban = assertDoesNotThrow(() -> random.next());
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
    void whenStructureIsKnown_thenSameIbanCountry() {
      RandomIban random = new RandomIban();

      Iban iban = random.next(BbanStructure.FR);

      assertEquals(IsoCountry.FR, iban.getCountry());
    }
  }
}
