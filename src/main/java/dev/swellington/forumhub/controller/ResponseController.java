package dev.swellington.forumhub.controller;


import dev.swellington.forumhub.domain.response.ResponseDetailDto;
import dev.swellington.forumhub.domain.response.ResponseRegisterDto;
import dev.swellington.forumhub.domain.response.ResponseService;
import dev.swellington.forumhub.domain.topic.Topic;
import dev.swellington.forumhub.domain.topic.TopicUpdateDto;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static dev.swellington.forumhub.controller.ResourceMapper.RESPONSE_RESOURCE;

@RestController
@RequestMapping(RESPONSE_RESOURCE)
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping
    public ResponseEntity<ResponseDetailDto> create(@RequestBody @Valid ResponseRegisterDto dto, UriComponentsBuilder uriBuilder){
        val response = responseService.createResponse(dto);
        return ResponseEntity.created(uriBuilder.path(RESPONSE_RESOURCE + "/{id}").buildAndExpand(response.getId()).toUri()).body(ResponseDetailDto.fromResponse(response));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseListDetailtDto>> getAll(@RequestParam(required = true, name = "topic") Long topicId, @PageableDefault(sort = {"createdAt"}) Pageable pageable){
        val responses = responseService.getAllByTopic(topicId, pageable);
        return ResponseEntity.ok(responses.map(ResponseListDetailtDto::fromResponse));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseDetailDto> show(@PathVariable Long id){
//        return null;
//    }

//    @PutMapping("")
//    public ResponseEntity<ResponseDetailDto> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateDto dto){
//        return null;
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        responseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
