package dev.swellington.forumhub.controller;


import dev.swellington.forumhub.domain.course.CourseDetailDto;
import dev.swellington.forumhub.domain.course.CourseRegisterDto;
import dev.swellington.forumhub.domain.course.CourseService;
import dev.swellington.forumhub.domain.course.CourseUpdateDto;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static dev.swellington.forumhub.controller.ResourceMapper.COURSE_RESOURCE;

@RestController
@RequestMapping(COURSE_RESOURCE)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDetailDto> create(@RequestBody @Valid CourseRegisterDto dto, UriComponentsBuilder uriBuilder) {
        val course = courseService.createCourse(dto);
        return ResponseEntity.created(uriBuilder.path(COURSE_RESOURCE + "/{id}").buildAndExpand(course.getId()).toUri()).body(CourseDetailDto.fromCourse(course));
    }

    @GetMapping
    public ResponseEntity<Page<CourseDetailDto>> getAll(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(courseService.getAll(pageable).map(CourseDetailDto::fromCourse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(CourseDetailDto.fromCourse(courseService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDetailDto> update(@PathVariable Long id, @RequestBody @Valid CourseUpdateDto dto) {
        return ResponseEntity.ok(CourseDetailDto.fromCourse( courseService.update( id, dto )));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
