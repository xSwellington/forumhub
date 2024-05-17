package dev.swellington.forumhub.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        @NotBlank
        @Size(min = 5, max = 20)
        @Pattern(regexp = "^[a-zA-Z0-9]([._\\-](?![._\\-])|[a-zA-Z0-9]){5,20}[a-zA-Z0-9]$")
        String name
) {
}
