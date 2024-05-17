package dev.swellington.forumhub.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(){
        super("O curso solicitado não existe.");
    }
}
