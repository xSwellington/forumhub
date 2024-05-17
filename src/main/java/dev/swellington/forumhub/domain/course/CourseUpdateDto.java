package dev.swellington.forumhub.domain.course;

import dev.swellington.forumhub.infra.annotations.AtLeastOneFieldNotNull;

@AtLeastOneFieldNotNull(fieldNames = {"name", "category"})
public record CourseUpdateDto(
        String name,
        String category
) {
}
