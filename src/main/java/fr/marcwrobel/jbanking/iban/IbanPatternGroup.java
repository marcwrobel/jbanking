package fr.marcwrobel.jbanking.iban;

import static java.util.Objects.requireNonNull;

import fr.marcwrobel.jbanking.swift.SwiftPatternCharacterRepresentation;
import java.io.Serializable;

class IbanPatternGroup implements Serializable {
  /**
   * Serialization version.
   */
  private static final long serialVersionUID = 0;

  final int from;
  final int length;
  final SwiftPatternCharacterRepresentation characters;

  IbanPatternGroup(int from, int length, SwiftPatternCharacterRepresentation characters) {
    this.from = from;
    this.length = length;
    this.characters = requireNonNull(characters);
  }

  boolean matches(String s) {
    // length check is done at SwiftPattern level
    for (int i = from; i < from + length; i++) {
      if (!characters.has(s.charAt(i))) {
        return false;
      }
    }

    return true;
  }
}
