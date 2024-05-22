package dev.swellington.forumhub.domain.response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    @Query("select r from Response r left join fetch r.user where r.topic.id = :topicId order by r.createdAt asc ")
    Page<Response> findAllByTopicId(Long topicId, Pageable pageable);
}
