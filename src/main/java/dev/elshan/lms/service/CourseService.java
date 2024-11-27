package dev.elshan.lms.service;

import dev.elshan.lms.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course addCourse(Course course);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
}
