package org.example.utils;

import java.util.regex.Pattern;

public class RegexBuilder {

    private StringBuilder pattern;
    private boolean caseInsensitive;
    private Pattern compiledPattern;

    public RegexBuilder() {
        this.pattern = new StringBuilder();
        this.caseInsensitive = false;
    }

    public RegexBuilder(Pattern precompiledPattern) {
        this.compiledPattern = precompiledPattern;
    }

    // Basic constructs
    public RegexBuilder literal(String text) {
        if (compiledPattern != null) throw new IllegalStateException("Cannot modify a precompiled pattern.");
        pattern.append(Pattern.quote(text));
        return this;
    }

    public RegexBuilder digit() {
        pattern.append("\\d");
        return this;
    }

    public RegexBuilder nonDigit() {
        pattern.append("\\D");
        return this;
    }

    public RegexBuilder word() {
        pattern.append("\\w");
        return this;
    }

    public RegexBuilder nonWord() {
        pattern.append("\\W");
        return this;
    }

    public RegexBuilder whitespace() {
        pattern.append("\\s");
        return this;
    }

    public RegexBuilder nonWhitespace() {
        pattern.append("\\S");
        return this;
    }

    // Character classes
    public RegexBuilder anyOf(String chars) {
        pattern.append("[").append(chars).append("]");
        return this;
    }

    public RegexBuilder anyCharacterExcept(String chars) {
        pattern.append("[^").append(Pattern.quote(chars)).append("]");
        return this;
    }

    public RegexBuilder noneOf(String chars) {
        pattern.append("[^").append(chars).append("]");
        return this;
    }

    // Quantifiers
    public RegexBuilder oneOrMore() {
        pattern.append("+");
        return this;
    }

    public RegexBuilder zeroOrMore() {
        pattern.append("*");
        return this;
    }

    public RegexBuilder optional() {
        pattern.append("?");
        return this;
    }

    public RegexBuilder exactly(int times) {
        pattern.append("{").append(times).append("}");
        return this;
    }

    public RegexBuilder atLeast(int times) {
        pattern.append("{").append(times).append(",}");
        return this;
    }

    public RegexBuilder between(int min, int max) {
        pattern.append("{").append(min).append(",").append(max).append("}");
        return this;
    }

    // Anchors and boundaries
    public RegexBuilder start() {
        pattern.insert(0, "^");
        return this;
    }

    public RegexBuilder end() {
        pattern.append("$");
        return this;
    }

    public RegexBuilder wordBoundary() {
        pattern.append("\\b");
        return this;
    }

    public RegexBuilder nonWordBoundary() {
        pattern.append("\\B");
        return this;
    }

    // Grouping
    public RegexBuilder group(RegexBuilder subPattern) {
        pattern.append("(").append(subPattern.build()).append(")");
        return this;
    }

    public RegexBuilder nonCapturingGroup(RegexBuilder subPattern) {
        pattern.append("(?:").append(subPattern.build()).append(")");
        return this;
    }

    public RegexBuilder namedGroup(String name, RegexBuilder subPattern) {
        pattern.append("(?<").append(name).append(">").append(subPattern.build()).append(")");
        return this;
    }

    // Modifiers and options
    public RegexBuilder caseInsensitive() {
        this.caseInsensitive = true;
        return this;
    }

    // Build and compile
    public String build() {
        StringBuilder finalPattern = new StringBuilder();

        if (caseInsensitive) finalPattern.append("(?i)");


        finalPattern.append(pattern.toString());
        return finalPattern.toString();
    }

    public Pattern compile() {
        int flags = 0;
        if (caseInsensitive) flags |= Pattern.CASE_INSENSITIVE;
        return Pattern.compile(this.build(), flags);
    }

    public boolean matches(String input) {
        if (compiledPattern != null) return compiledPattern.matcher(input).matches();
        return compile().matcher(input).matches();
    }

    public String extract() {
        if (compiledPattern != null) return compiledPattern.pattern();
        return this.build();
    }

    public RegexBuilder reset() {
        this.pattern.setLength(0);
        return this;
    }

    public String replaceAll(String input, String replacement) {
        if (compiledPattern != null) return compiledPattern.matcher(input).replaceAll(replacement);
        return compile().matcher(input).replaceAll(replacement);
    }
}
