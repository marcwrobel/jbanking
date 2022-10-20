package fr.marcwrobel.jbanking.internal;

import org.junit.jupiter.api.Assertions;

/**
 * Some test utilities.
 */
public class TestUtils {

  public static final String BLANK = " \t\n ";

  public static void shouldHaveThrown(Class<? extends Throwable> exceptionClass) {
    Assertions.fail("Should have thrown " + exceptionClass.getName());
  }
}
