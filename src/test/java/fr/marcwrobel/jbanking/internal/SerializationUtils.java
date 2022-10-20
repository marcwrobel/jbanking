package fr.marcwrobel.jbanking.internal;

import static java.util.Objects.requireNonNull;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * A class to simplify serialization and deserialization of Java objects.
 *
 * <p>
 * It was heavily inspired by
 * <a href="https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/SerializationUtils.java">
 * Apache commons-lang SerializationUtils</a> and by <a href="https://www.baeldung.com/java-validate-serializable">
 * Serialization Validation in Java</a>.
 *
 * @since 4.0.0
 */
public class SerializationUtils {

  private SerializationUtils() {
    // prevent instantiation
  }

  /**
   * Check whether the given class is serializable.
   * 
   * @param clazz a non-null {@link Class}
   * @return {@code true} if the class is serializable, {@code false} otherwise
   */
  public static boolean isSerializable(Class<?> clazz) {
    boolean serializable = clazz.isPrimitive() || clazz.isInterface() || Serializable.class.isAssignableFrom(clazz);
    if (!serializable) {
      return false;
    }

    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field field : declaredFields) {
      if (Modifier.isVolatile(field.getModifiers()) || Modifier.isTransient(field.getModifiers()) ||
          Modifier.isStatic(field.getModifiers())) {
        continue;
      }

      Class<?> fieldType = field.getType();
      if (!isSerializable(fieldType)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Deserializes a single {@link Object} from an array of bytes.
   *
   * <p>
   * If the call site incorrectly types the return value, a {@link ClassCastException} is thrown from the call site.
   * Without Generics in this declaration, the call site must type cast and can cause the same ClassCastException.
   * Note that in both cases, the ClassCastException is in the call site, not in this method.
   * </p>
   *
   * @param <T> the object type to be deserialized
   * @param objectData the serialized object, must not be null
   * @return the deserialized object
   * @throws NullPointerException if {@code objectData} is {@code null}
   * @throws IllegalArgumentException (runtime) if the serialization fails
   */
  public static <T> T deserialize(final byte[] objectData) {
    try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(requireNonNull(objectData)))) {
      @SuppressWarnings("unchecked")
      final T obj = (T) in.readObject();
      return obj;
    } catch (final ClassNotFoundException | IOException ex) {
      throw new IllegalArgumentException(ex);
    }
  }

  /**
   * Serializes an {@link Object} to a byte array for
   * storage/serialization.
   *
   * @param obj the object to serialize to bytes
   * @return a byte[] with the converted Serializable
   * @throws IllegalArgumentException (runtime) if the serialization fails
   */
  public static byte[] serialize(final Serializable obj) {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream(512);

    try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
      out.writeObject(obj);
    } catch (final IOException ex) {
      throw new IllegalArgumentException(ex);
    }

    return baos.toByteArray();
  }
}
