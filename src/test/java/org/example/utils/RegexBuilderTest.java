package org.example.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexBuilderTest {

    @Test
    public void testLiteral() {
        RegexBuilder regex = new RegexBuilder().literal("hello").end();
        assertTrue(regex.matches("hello"));
        assertFalse(regex.matches("Hello"));
    }

    @Test
    public void testDigit() {
        RegexBuilder regex = new RegexBuilder().digit().oneOrMore();
        assertEquals("\\d+", regex.extract());
        assertTrue(regex.matches("123"));
        assertFalse(regex.matches("abc"));
    }

    @Test
    public void testWhitespace() {
        RegexBuilder regex = new RegexBuilder().whitespace().oneOrMore();
        assertEquals("\\s+", regex.extract());
        assertTrue(regex.matches(" "));
        assertTrue(regex.matches("   "));
        assertFalse(regex.matches("abc"));
    }

    @Test
    public void testAnyOf() {
        RegexBuilder regex = new RegexBuilder().anyOf("abc");
        assertEquals("[abc]", regex.extract());
        assertTrue(regex.matches("a"));
        assertTrue(regex.matches("b"));
        assertFalse(regex.matches("d"));
    }


    @Test
    public void testGroup() {
        RegexBuilder regex = new RegexBuilder().group(new RegexBuilder().literal("hello")).digit().oneOrMore();
        assertEquals("(hello)\\d+", regex.extract());
        assertTrue(regex.matches("hello123"));
        assertFalse(regex.matches("helloabc"));
    }


    @Test
    public void testCaseInsensitive() {
        RegexBuilder regex = new RegexBuilder().literal("hello").caseInsensitive();
        assertTrue(regex.matches("hello"));
        assertTrue(regex.matches("HELLO"));
    }


    @Test
    public void testComplexPattern() {
        RegexBuilder regex = new RegexBuilder()
                .start()
                .literal("User")
                .whitespace()
                .digit().oneOrMore()
                .literal("@example.com")
                .end();
        assertTrue(regex.matches("User 123@example.com"));
        assertFalse(regex.matches("User abc@example.com"));
    }

    @Test
    public void testReplaceAll() {
        RegexBuilder regex = new RegexBuilder()
                .literal("<")
                .anyCharacterExcept(">")
                .oneOrMore()
                .literal(">");

        String input = "<tag>('Hello')</tag>";
        String sanitized = regex.replaceAll(input, "");
        assertEquals("('Hello')", sanitized);
    }

    @Test
    public void testReset() {
        RegexBuilder regex = new RegexBuilder().literal("hello").reset().literal("world");
        assertTrue(regex.matches("world"));
        assertFalse(regex.matches("hello"));
    }
}