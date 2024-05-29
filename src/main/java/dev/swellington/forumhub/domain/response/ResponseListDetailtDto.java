package dev.swellington.forumhub.domain.response;

import dev.swellington.forumhub.domain.user.UserDetailDto;

import java.time.LocalDateTime;

public record ResponseListDetailtDto(
        Long id,
        String message,
        String solution,
        LocalDateTime createdAt,
        UserDetailDto user) {
    public static ResponseListDetailtDto fromResponse(Response response) {
        return new ResponseListDetailtDto(response.getId(), response.getMessage(), response.getSolution(),
                response.getCreatedAt(),
                UserDetailDto.fromUser(response.getUser()));
    }
}
