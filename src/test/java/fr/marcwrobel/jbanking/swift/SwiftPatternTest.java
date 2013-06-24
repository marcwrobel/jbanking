/**
 * Copyright 2013 Marc Wrobel (marc.wrobel@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.marcwrobel.jbanking.swift;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link SwiftPattern} class.
 *
 * @author Marc Wrobel
 */
public class SwiftPatternTest {

    @Test(expected = IllegalArgumentException.class)
    public void aSwiftPatternCannotBeNull() {
        SwiftPattern.compile(null);
    }

    @Test(expected = SwiftPatternSyntaxException.class)
    public void aSwiftPatternMustBeWellFormed() {
        String invalidPattern = "2!n3d";
        try {
            SwiftPattern.compile("2!n3d");
        } catch (SwiftPatternSyntaxException e) {
            assertEquals(invalidPattern, e.getExpression());
            throw e;
        }
    }

    @Test
    public void digitsFormatTest() {
        String expression = "10n";
        assertMatches("1", expression);
        assertMatches("12", expression);
        assertNotMatches("01234567890", expression);
        assertNotMatches("1a", expression);
    }

    @Test
    public void strictDigitsFormatTest() {
        String expression = "5!n";
        assertMatches("12345", expression);
        assertNotMatches("123", expression);
        assertNotMatches("123456", expression);
        assertNotMatches("1a", expression);
    }

    @Test
    public void upperCaseLettersFormatTest() {
        String expression = "2a";
        assertMatches("A", expression);
        assertMatches("AB", expression);
        assertNotMatches("ABC", expression);
        assertNotMatches("1A", expression);
    }

    @Test
    public void strictUpperCaseLettersFormatTest() {
        String expression = "5!a";
        assertMatches("ABCDE", expression);
        assertNotMatches("ABC", expression);
        assertNotMatches("ABCDEF", expression);
        assertNotMatches("1A", expression);
    }

    @Test
    public void upperAndLowerCaseAlphanumericsFormatTest() {
        String expression = "2c";
        assertMatches("1", expression);
        assertMatches("A", expression);
        assertMatches("Ab", expression);
        assertMatches("12", expression);
        assertMatches("1A", expression);
        assertNotMatches("1!", expression);
        assertNotMatches("ABC", expression);
        assertNotMatches("AB1", expression);
    }

    @Test
    public void strictUpperAndLowerCaseAlphanumericsFormatTest() {
        String expression = "5!c";
        assertMatches("Ab1De", expression);
        assertNotMatches("Ab1", expression);
        assertNotMatches("Ab1De3", expression);
        assertNotMatches("1111", expression);
        assertNotMatches("aaaaaa", expression);
    }

    @Test
    public void multipleBasicExpressionTest() {
        String expression = "2!c18!c";
        assertMatches("01234567890123456789", expression);
        assertNotMatches("Ab1", expression);
        assertNotMatches("Ab1De3", expression);
        assertNotMatches("1111", expression);
        assertNotMatches("aaaaaa", expression);
    }

    @Test
    public void mixedFormatTest() {
        String expression = "2!n3!c1!a2e";
        assertMatches("12a1cC  ", expression);
        assertNotMatches("12", expression);
        assertNotMatches("123", expression);
        assertNotMatches("1a", expression);
        assertNotMatches("12ac2", expression);
        assertNotMatches("12ac2  ", expression);
    }

    private void assertMatches(String value, String expression) {
        SwiftPattern pattern = SwiftPattern.compile(expression);
        assertTrue(String.format("%s should match %s", value, pattern), pattern.matcher(value).matches());
    }

    private void assertNotMatches(String value, String expression) {
        SwiftPattern pattern = SwiftPattern.compile(expression);
        assertFalse(String.format("%s should not match %s", value, pattern), pattern.matcher(value).matches());
    }

    @Test
    public void transformationTest() {
        String expression = "5!n";
        SwiftPattern pattern = SwiftPattern.compile(expression);
        assertEquals(expression, pattern.getExpression());
        assertEquals("^[0-9]{5}$", pattern.getEquivalentJavaPattern().pattern());
    }

    @Test
    public void equalityTest() {
        SwiftPattern pattern1 = SwiftPattern.compile("4!n");
        SwiftPattern pattern2 = SwiftPattern.compile("4!n");

        assertTrue(pattern1.equals(pattern1));
        assertTrue(pattern2.equals(pattern2));

        assertTrue(pattern1.equals(pattern2));
        assertTrue(pattern2.equals(pattern1));
        assertTrue(pattern1.hashCode() == pattern2.hashCode());

        SwiftPattern pattern3 = SwiftPattern.compile("3!n");
        assertFalse(pattern1.equals(null));
        assertFalse(pattern1.equals(new Object()));
        assertFalse(pattern1.equals(pattern3));
    }

}
