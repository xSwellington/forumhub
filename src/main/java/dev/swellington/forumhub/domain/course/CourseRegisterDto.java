package dev.swellington.forumhub.domain.course;

import jakarta.validation.constraints.NotBlank;

public record CourseRegisterDto(
        @NotBlank
        String name,

        @NotBlank
        String category
) {
}
