package dev.swellington.forumhub.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String s) {
        super(s);
    }
}
