package dev.swellington.forumhub.domain.user;

import jakarta.validation.constraints.*;

public record UserRegisterDto(
        @NotBlank
        @Size(min = 5, max = 20)
        @Pattern(regexp = "^[a-zA-Z0-9]([._\\-](?![._\\-])|[a-zA-Z0-9]){5,20}[a-zA-Z0-9]$")
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8, max = 20)
        String password
) {
}
