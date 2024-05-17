package dev.swellington.forumhub.domain.topic;

import dev.swellington.forumhub.domain.user.UserDetailDto;
import lombok.val;

import java.time.LocalDateTime;

public record TopicDetailDto(
        Long id,
        String title,
        String message,
        TopicStatus status,
        LocalDateTime createdAt,
        UserDetailDto author
) {
    public static TopicDetailDto fromTopic(Topic topic) {
        return new TopicDetailDto(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getCreatedAt(),
                UserDetailDto.fromUser(topic.getAuthor())
        );
    }
}
