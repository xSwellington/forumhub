package dev.swellington.forumhub.exception;

public class UserAlreadyRegisteredWithEmailException extends RuntimeException {
    public UserAlreadyRegisteredWithEmailException(){
        super("Já existe um usuário cadastrado com este e-mail.");
    }
}
