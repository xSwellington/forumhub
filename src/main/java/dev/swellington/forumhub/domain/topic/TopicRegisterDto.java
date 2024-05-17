package dev.swellington.forumhub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegisterDto(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long userId,
        @NotNull
        Long courseId
) {
}
