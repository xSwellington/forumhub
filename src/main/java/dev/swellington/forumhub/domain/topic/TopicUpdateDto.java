package dev.swellington.forumhub.domain.topic;

import dev.swellington.forumhub.infra.annotations.AtLeastOneFieldNotNull;

@AtLeastOneFieldNotNull(fieldNames = {"title", "message"})
public record TopicUpdateDto(
        String title,
        String message
) {
}
