package dev.swellington.forumhub.domain.user;

import java.time.LocalDateTime;

public record UserDetailDto(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt
        ) {
    public static UserDetailDto fromUser(User user) {
        return new UserDetailDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
