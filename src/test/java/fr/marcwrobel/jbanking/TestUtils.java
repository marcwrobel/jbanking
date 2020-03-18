package fr.marcwrobel.jbanking;

import org.junit.Assert;

/**
 * Some test utilities.
 *
 * @author Marc Wrobel
 */
public class TestUtils {

  public static final String BLANK = " \t\n ";

  public static void shouldHaveThrown(Class<? extends Throwable> exceptionClass) {
    Assert.fail("Should have thrown " + exceptionClass.getName());
  }
}
