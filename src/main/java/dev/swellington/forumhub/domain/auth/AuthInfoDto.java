package dev.swellington.forumhub.domain.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthInfoDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {
}
