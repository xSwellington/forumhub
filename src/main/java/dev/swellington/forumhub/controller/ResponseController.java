package dev.swellington.forumhub.controller;


import dev.swellington.forumhub.domain.response.ResponseDetailDto;
import dev.swellington.forumhub.domain.response.ResponseRegisterDto;
import dev.swellington.forumhub.domain.topic.TopicUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static dev.swellington.forumhub.controller.ResourceMapper.RESPONSE_RESOURCE;

@RestController
@RequestMapping(RESPONSE_RESOURCE)
public class ResponseController {

    @PostMapping("/{topicId}")
    public ResponseEntity<ResponseDetailDto> create(@PathVariable Long topicId, @RequestBody @Valid ResponseRegisterDto dto, UriComponentsBuilder uriBuilder){
        return null;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDetailDto>> getAll(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDetailDto> show(@PathVariable Long id){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDetailDto> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateDto dto){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return null;
    }

}
