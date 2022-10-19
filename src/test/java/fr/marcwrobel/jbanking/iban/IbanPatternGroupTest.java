package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.swift.SwiftPatternCharacterRepresentation.DIGITS;
import static fr.marcwrobel.jbanking.swift.SwiftPatternCharacterRepresentation.UPPER_CASE_LETTERS;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IbanPatternGroupTest {

  @Test
  void charactersCannotBeNull() {
    assertThrows(NullPointerException.class, () -> new IbanPatternGroup(null, 0, 1));
  }

  @Test
  void differentCharactersCannotBeMerged() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(UPPER_CASE_LETTERS, 1, 1);

    assertFalse(group1.canBeMergedTo(group2));
  }

  @Test
  void aGroupCannotBeMergedToItself() {
    IbanPatternGroup group = new IbanPatternGroup(DIGITS, 0, 1);

    assertFalse(group.canBeMergedTo(group));
  }

  @Test
  void nonConsecutiveGroupsCannotBeMerged() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(DIGITS, 2, 1);

    assertFalse(group1.canBeMergedTo(group2));
  }

  @Test
  void consecutiveGroupsCanBeMerged() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(DIGITS, 1, 1);

    assertTrue(group1.canBeMergedTo(group2));
  }

  @Test
  void consecutiveGroupsCanBeMergedInReverseOrder() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(DIGITS, 1, 1);

    assertFalse(group2.canBeMergedTo(group1));
  }

  @Test
  void mergeGroups() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(DIGITS, 1, 1);

    IbanPatternGroup mergedGroup = group1.merge(group2);

    assertEquals(DIGITS, mergedGroup.characters);
    assertEquals(0, mergedGroup.from);
    assertEquals(2, mergedGroup.length);
  }

  @ParameterizedTest
  @ValueSource(strings = { "ab1", "097" })
  void matches(String s) {
    IbanPatternGroup group = new IbanPatternGroup(DIGITS, 2, 1);

    assertTrue(group.matches(s));
  }

  @ParameterizedTest
  @ValueSource(strings = { "", "0", "ab" }) // length check is handled in the calling class
  void doesntMatchAndThrows(String s) {
    IbanPatternGroup group = new IbanPatternGroup(DIGITS, 2, 1);

    assertThrows(StringIndexOutOfBoundsException.class, () -> group.matches(s));
  }

  @ParameterizedTest
  @ValueSource(strings = { "abc", "abcd" })
  void doesntMatch(String s) {
    IbanPatternGroup group = new IbanPatternGroup(DIGITS, 2, 1);

    assertFalse(group.matches(s));
  }
}
