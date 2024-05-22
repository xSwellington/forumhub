package dev.swellington.forumhub.infra.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.swellington.forumhub.configuration.JwtConfiguration;
import dev.swellington.forumhub.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Autowired
    private JwtConfiguration jwtConfiguration;

    private String getTokenFromRequest(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    public String generateToken(final UserDetails userDetails) {
        val username = userDetails.getUsername();
        val roles = userDetails.getAuthorities();
        return JWT.create().withIssuer(jwtConfiguration.getIssuer())
                .withExpiresAt(LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00")))
                .withSubject(username)
                .withClaim("roles", roles.stream().map(GrantedAuthority::getAuthority).toList())
                .sign(Algorithm.HMAC256(jwtConfiguration.getSecret()));

    }

    public DecodedJWT validateToken(final HttpServletRequest request) {
        val token = getTokenFromRequest(request);
        if (token != null) {
            return JWT.require(Algorithm.HMAC256(jwtConfiguration.getSecret()))
                    .withIssuer(jwtConfiguration.getIssuer())
                    .build().verify(token);
        }
        return null;
    }
}
