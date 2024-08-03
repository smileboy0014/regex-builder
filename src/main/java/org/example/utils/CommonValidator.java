package org.example.utils;

public class CommonValidator {

    private static final RegexBuilder USERNAME_PATTERN = new RegexBuilder()
            .start()
            .word().oneOrMore()
            .end();

    private static final RegexBuilder EMAIL_PATTERN = new RegexBuilder()
            .start()
            .word().oneOrMore()
            .literal("@")
            .word().oneOrMore()
            .literal(".")
            .word().oneOrMore()
            .end();

    public boolean validateUsername(String username) {
        System.out.println("Username Pattern: " + USERNAME_PATTERN.extract());
        return USERNAME_PATTERN.matches(username);
    }

    public boolean validateEmail(String email) {
        System.out.println("Email Pattern: " + EMAIL_PATTERN.extract());
        return EMAIL_PATTERN.matches(email);
    }

}
