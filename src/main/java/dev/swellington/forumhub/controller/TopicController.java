package dev.swellington.forumhub.controller;

import dev.swellington.forumhub.domain.response.ResponseDetailDto;
import dev.swellington.forumhub.domain.topic.TopicDetailDto;
import dev.swellington.forumhub.domain.topic.TopicRegisterDto;
import dev.swellington.forumhub.domain.topic.TopicService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static dev.swellington.forumhub.controller.ResourceMapper.TOPIC_RESOURCE;

@RestController
@RequestMapping(TOPIC_RESOURCE)
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDetailDto> create(@RequestBody @Valid TopicRegisterDto dto, UriComponentsBuilder uriBuilder) {
        val topic = topicService.createTopic(dto);
        return ResponseEntity.created(uriBuilder.path(TOPIC_RESOURCE + "/{id}").buildAndExpand(topic.getId()).toUri()).body(TopicDetailDto.fromTopic(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicDetailDto>> getAll(@PageableDefault(sort = {"createdAt"}) Pageable pageable) {
        return ResponseEntity.ok(topicService.getAll(pageable).map(TopicDetailDto::fromTopic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(TopicDetailDto.fromTopic(topicService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/responses")
    public ResponseEntity<Page<ResponseDetailDto>> getTopicWithResponse(@PathVariable Long id, @PageableDefault(sort = {"createdAt"}) Pageable pageable) {
        return ResponseEntity.ok(topicService.getAllResponse(id, pageable).map(ResponseDetailDto::fromResponse));
    }
}
