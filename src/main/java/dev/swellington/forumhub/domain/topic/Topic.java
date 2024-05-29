package dev.swellington.forumhub.domain.topic;

import dev.swellington.forumhub.domain.course.Course;
import dev.swellington.forumhub.domain.response.Response;
import dev.swellington.forumhub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Topic")
@Table(name = "topics")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Setter
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated
    @NotNull
    private TopicStatus status;

    @NotNull
    @JoinColumn(nullable = false, name = "author_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User author;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @ToString.Exclude
    private Course course;

    @OneToMany(mappedBy = "topic")
    @Builder.Default
    @ToString.Exclude
    private Set<Response> responses = new LinkedHashSet<>();

    @PrePersist
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now();
    }

}
