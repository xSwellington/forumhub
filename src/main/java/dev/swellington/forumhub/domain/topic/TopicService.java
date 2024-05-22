package dev.swellington.forumhub.domain.topic;

import dev.swellington.forumhub.domain.course.Course;
import dev.swellington.forumhub.domain.course.CourseRepository;
import dev.swellington.forumhub.domain.course.CourseUpdateDto;
import dev.swellington.forumhub.domain.response.Response;
import dev.swellington.forumhub.domain.response.ResponseRepository;
import dev.swellington.forumhub.domain.user.UserRepository;
import dev.swellington.forumhub.exception.TopicAlreadyExistWhenSameContent;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Topic createTopic(TopicRegisterDto dto) {

        if (topicRepository.existsByTitleLikeIgnoreCaseOrMessageLikeIgnoreCaseAllIgnoreCase(dto.title(), dto.message()))
            throw new TopicAlreadyExistWhenSameContent();

        val user = userRepository.getReferenceById(dto.userId());
        val course = courseRepository.getReferenceById(dto.courseId());
        val topic = Topic.builder()
                .message(dto.message())
                .title(dto.title())
                .author(user)
                .course(course)
                .status(TopicStatus.ACTIVE)
                .build();
        topicRepository.save(topic);
        return topic;
    }

    public Page<Topic> getAll(Pageable pageable) {
        return topicRepository.findAllFetchAuthor(pageable);
    }

    public Topic getById(Long id) {
        return topicRepository.findByIdFetchAuthor(id).orElseThrow(EntityNotFoundException::new);
    }

    public void delete(Long id) {
        topicRepository.deleteById(id);
    }

    public Page<Response> getAllResponse(Long id, Pageable pageable) {
        return topicRepository.findAllByIdResponses(id, pageable);
    }

    public Topic update(Long id, TopicUpdateDto dto) {

        val othertopic = topicRepository.findByTitleOrMessage(dto.title(), dto.message());
        if (othertopic.isPresent() && (!othertopic.get().getId().equals(id))) {
            throw new TopicAlreadyExistWhenSameContent();
        }

        val topic = topicRepository.getReferenceById(id);


        if (dto.message() != null || dto.title() != null) {
            if (dto.message() != null && !dto.message().trim().isBlank()) topic.setMessage(dto.message());
            if (dto.title() != null && !dto.title().trim().isBlank()) topic.setTitle(dto.title());
        } else {
            throw new ValidationException("Parâmetros incorretos. É necessário passar uma mensagem o/ou título.");
        }

        return topicRepository.save(topic);
    }
}
