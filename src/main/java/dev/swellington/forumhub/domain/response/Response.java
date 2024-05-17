package dev.swellington.forumhub.domain.response;

import dev.swellington.forumhub.domain.topic.Topic;
import dev.swellington.forumhub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Entity(name = "Response")
@Table(name = "responses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String message;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotBlank
    private String solution;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now();
    }
}
