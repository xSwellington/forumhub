package dev.swellington.forumhub.exception;

public class TopicAlreadyExistWhenSameContent extends RuntimeException {
    public TopicAlreadyExistWhenSameContent() {
        super("Já existe um tópico com o mesmo conteúdo.");
    }
}
