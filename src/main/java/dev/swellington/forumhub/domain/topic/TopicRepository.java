package dev.swellington.forumhub.domain.topic;

import dev.swellington.forumhub.domain.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("select t from Topic t left join fetch t.author")
    Page<Topic> findAllFetchAuthor(Pageable pageable);

    @Query("select t from Topic t left join fetch t.author")
    Topic findByIdFetchAuthor(Long id);

    @Query("select r from Response r where r.topic.id = :id")
    Page<Response> findAllByIdResponses(Long id, Pageable pageable);
}
