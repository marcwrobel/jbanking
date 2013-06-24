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

/**
 * Thrown to indicate a syntax error in a SWIFT expression pattern.
 *
 * @author Marc Wrobel
 * @see SwiftPattern
 * @since 1.0
 */
public class SwiftPatternSyntaxException extends RuntimeException {

    private final String expression;

    /**
     * Constructs a {@code SwiftPatternSyntaxException} with the expression that caused the error and the given detail message.
     *
     * @param expression a string
     */
    SwiftPatternSyntaxException(String expression) {
        super(String.format("the expression syntax is invalid in '%s'", expression));
        this.expression = expression;
    }

    /**
     * Returns the input expression that caused this exception to be raised.
     *
     * @return a string
     */
    public String getExpression() {
        return expression;
    }

}
