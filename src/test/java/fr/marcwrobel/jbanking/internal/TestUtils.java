package fr.marcwrobel.jbanking.internal;

import static fr.marcwrobel.jbanking.internal.SerializationUtils.deserialize;
import static fr.marcwrobel.jbanking.internal.SerializationUtils.isSerializable;
import static fr.marcwrobel.jbanking.internal.SerializationUtils.serialize;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;

/**
 * Some test utilities.
 */
public class TestUtils {

  public static final String BLANK = " \t\n ";

  public static <T> void testEquality(T a, T b) {
    assertThat(a).isEqualTo(a).isEqualTo(b);
    assertThat(b).isEqualTo(b).isEqualTo(a);
    assertThat(a).hasSameHashCodeAs(b);
    assertThat(a).hasToString(b.toString());

    // do not modify - bullshit tests to improve coverage and have a better visibility in sonar
    assertThat(a.equals(null)).isFalse();
    assertThat(a.equals(new Object())).isFalse();
  }

  public static <T extends Serializable> void testSerialization(T object) {
    Class<?> clazz = object.getClass();
    byte[] serializedObject = serialize(object);
    Serializable deserializedObject = deserialize(serializedObject);

    assertThat(deserializedObject).isEqualTo(object);
    assertThat(isSerializable(clazz)).isTrue();
  }
}
