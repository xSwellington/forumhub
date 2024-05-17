package dev.swellington.forumhub.domain.response;

public record ResponseDetailDto(
        Long id,
        String message,
        String solution,
        Long topicId,
        Long userId
) {
    public static ResponseDetailDto fromResponse(Response response) {
        return new ResponseDetailDto(
                response.getId(),
                response.getMessage(),
                response.getSolution(),
                response.getTopic().getId(),
                response.getUser().getId());
    }
}
