package dev.elshan.lms.service.impl;

import dev.elshan.lms.model.Course;
import dev.elshan.lms.repository.CourseRepository;
import dev.elshan.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;

    @Override
    @Cacheable(value = "courses")
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND,"This course does not exist with given id: " + id));
    }

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public Course addCourse(Course course) {
        return repository.save(course);
    }

    @Override
    @CachePut(value = "courses", key = "#result.id")
    public Course updateCourse(Long id,Course course) {
        var foundedCourse = getCourseById(id);
        foundedCourse.setName(course.getName());
        foundedCourse.setDescription(course.getDescription());
        return repository.save(foundedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        repository.deleteById(id);
    }
}
