package dev.swellington.forumhub.exception;

public class CourseAlreadyExistWhenSameNameException extends RuntimeException {
    public CourseAlreadyExistWhenSameNameException(){
        super("Já existe um curso com o mesmo nome");
    }
}
