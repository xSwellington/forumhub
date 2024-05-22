package dev.swellington.forumhub.domain.response;

import dev.swellington.forumhub.domain.topic.TopicRepository;
import dev.swellington.forumhub.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private UserRepository userRepository;

    public Response createResponse(ResponseRegisterDto dto) {
        val user = userRepository.findById(dto.userId()).orElseThrow(()->new EntityNotFoundException("Usuário com ID " + dto.userId() + " não encontrado."));
        val topic = topicRepository.findById(dto.topicId()).orElseThrow(()->new EntityNotFoundException("Tópico com ID " + dto.topicId() + " não encontrado."));
        val response = Response.builder()
                .message(dto.message())
                .solution(dto.solution())
                .user(user)
                .topic(topic)
                .build();
        responseRepository.save(response);
        return response;
    }

    public void delete(Long id) {
        responseRepository.deleteById(id);
    }

    public Page<Response> getAllByTopic(Long topicId, Pageable pageable) {
        return responseRepository.findAllByTopicId(topicId, pageable);
    }
}
