package fr.marcwrobel.jbanking.iban;

import static fr.marcwrobel.jbanking.swift.SwiftPatternCharacterRepresentation.DIGITS;
import static fr.marcwrobel.jbanking.swift.SwiftPatternCharacterRepresentation.UPPER_CASE_LETTERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IbanPatternGroupTest {

  @Test
  void charactersCannotBeNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new IbanPatternGroup(null, 0, 1));
  }

  @Test
  void differentCharactersCannotBeMerged() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(UPPER_CASE_LETTERS, 1, 1);

    assertThat(group1.canBeMergedTo(group2)).isFalse();
  }

  @Test
  void aGroupCannotBeMergedToItself() {
    IbanPatternGroup group = new IbanPatternGroup(DIGITS, 0, 1);

    assertThat(group.canBeMergedTo(group)).isFalse();
  }

  @Test
  void nonConsecutiveGroupsCannotBeMerged() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(DIGITS, 2, 1);

    assertThat(group1.canBeMergedTo(group2)).isFalse();
  }

  @Test
  void consecutiveGroupsCanBeMerged() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(DIGITS, 1, 1);

    assertThat(group1.canBeMergedTo(group2)).isTrue();
  }

  @Test
  void consecutiveGroupsCanBeMergedInReverseOrder() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(DIGITS, 1, 1);

    assertThat(group2.canBeMergedTo(group1)).isFalse();
  }

  @Test
  void mergeGroups() {
    IbanPatternGroup group1 = new IbanPatternGroup(DIGITS, 0, 1);
    IbanPatternGroup group2 = new IbanPatternGroup(DIGITS, 1, 1);

    IbanPatternGroup mergedGroup = group1.merge(group2);

    assertThat(mergedGroup.characters).isEqualTo(DIGITS);
    assertThat(mergedGroup.from).isZero();
    assertThat(mergedGroup.length).isEqualTo(2);
  }

  @ParameterizedTest
  @ValueSource(strings = { "ab1", "097" })
  void matches(String s) {
    IbanPatternGroup group = new IbanPatternGroup(DIGITS, 2, 1);

    assertThat(group.matches(s)).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = { "", "0", "ab" }) // length check is handled in the calling class
  void doesntMatchAndThrows(String s) {
    IbanPatternGroup group = new IbanPatternGroup(DIGITS, 2, 1);

    assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> group.matches(s));
  }

  @ParameterizedTest
  @ValueSource(strings = { "abc", "abcd" })
  void doesntMatch(String s) {
    IbanPatternGroup group = new IbanPatternGroup(DIGITS, 2, 1);

    assertThat(group.matches(s)).isFalse();
  }
}
