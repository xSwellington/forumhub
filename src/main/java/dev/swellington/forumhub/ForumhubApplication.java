package dev.swellington.forumhub;

import dev.swellington.forumhub.configuration.JwtConfiguration;
import dev.swellington.forumhub.domain.topic.Topic;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumhubApplication {

    @Autowired
    private JwtConfiguration jwtConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ForumhubApplication.class, args);
    }
}
