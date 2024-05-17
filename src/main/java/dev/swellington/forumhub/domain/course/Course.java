package dev.swellington.forumhub.domain.course;


import dev.swellington.forumhub.domain.topic.Topic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Course")
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "course")
    @Builder.Default private Set<Topic> topics = new LinkedHashSet<>();


    @PrePersist
    public void onPrePersist(){
        this.createdAt = LocalDateTime.now();
    }


}
