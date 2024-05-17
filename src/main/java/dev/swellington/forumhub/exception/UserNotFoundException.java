package dev.swellington.forumhub.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("Usuário não encontrado");
    }
}
