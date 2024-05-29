package dev.swellington.forumhub.controller;

import dev.swellington.forumhub.domain.auth.AuthResponseDto;
import dev.swellington.forumhub.domain.auth.AuthInfoDto;
import dev.swellington.forumhub.infra.auth.JwtTokenService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.swellington.forumhub.controller.ResourceMapper.AUTH_RESOURCE;

@RestController
@RequestMapping(AUTH_RESOURCE)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping
    protected ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthInfoDto loginDto) {
        val authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha());
        val authentication = authenticationManager.authenticate(authenticationToken);
        val token = jwtTokenService.generateToken((UserDetails) authentication.getPrincipal() );
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
