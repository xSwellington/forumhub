package dev.swellington.forumhub.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseRegisterDto(
        @NotBlank
        String message,

        @NotBlank
        String solution,

        @NotNull
        Long topicId,

        @NotNull
        Long userId
) {
}
