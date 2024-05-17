package dev.swellington.forumhub.domain.course;

import dev.swellington.forumhub.exception.CourseAlreadyExistWhenSameNameException;
import dev.swellington.forumhub.exception.CourseNotFoundException;
import jakarta.validation.ValidationException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(CourseRegisterDto dto) {
        if (courseRepository.existsByNameIgnoreCase(dto.name())) throw new CourseAlreadyExistWhenSameNameException();

        val course = Course.builder()
                .id(null)
                .name(dto.name())
                .category(dto.category())
                .build();
        return courseRepository.save(course);
    }

    public Page<Course> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    public Course update(Long id, CourseUpdateDto dto) {
        val course = courseRepository.getReferenceById(id);
        if (dto.category() != null || dto.name() != null) {
            if (dto.name() != null) course.setName(dto.name());
            if (dto.category() != null) course.setCategory(dto.category());
        } else {
            throw new ValidationException("Parâmetros incorretos.");
        }
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
