package dev.swellington.forumhub.domain.course;

import lombok.val;

import java.time.LocalDateTime;


public record CourseDetailDto(
        Long id,
        String name,
        String category,
        LocalDateTime createdAt) {
    public static CourseDetailDto fromCourse(Course course) {
        return new CourseDetailDto(course.getId(), course.getName(), course.getCategory(), course.getCreatedAt());
    }
}