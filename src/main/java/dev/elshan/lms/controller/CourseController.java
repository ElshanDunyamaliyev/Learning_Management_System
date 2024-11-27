package dev.elshan.lms.controller;

import dev.elshan.lms.model.Course;
import dev.elshan.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService service;

    @GetMapping
    public List<Course> getAll() {
        System.out.println("Something");
        return service.getAllCourses();
    }

}
