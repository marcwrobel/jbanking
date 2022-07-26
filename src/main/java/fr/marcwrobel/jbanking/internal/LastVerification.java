package fr.marcwrobel.jbanking.internal;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.*;

/**
 * Document the last verification date of a list (of countries, of currencies...).
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE })
public @interface LastVerification {

  /**
   * Returns the last verification date of the annotated element.
   *
   * @return a non null date formatted as {@code yyyy-MM-dd} (e.g. 2022-07-20).
   */
  String value();
}
