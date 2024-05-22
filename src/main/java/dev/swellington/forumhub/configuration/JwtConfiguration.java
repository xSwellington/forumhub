package dev.swellington.forumhub.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtConfiguration {

    @Value("${api.security.token.issuer}")
    private String issuer;

    @Value("${api.security.token.secret}")
    private String secret = "";

}
