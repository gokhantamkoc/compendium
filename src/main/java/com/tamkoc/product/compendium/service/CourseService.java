package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.CourseNotFoundException;
import com.tamkoc.product.compendium.model.Course;
import com.tamkoc.product.compendium.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
@Service
public class CourseService implements ICourseService{
    @Resource
    CourseRepository courseRepository;

    @Override
    @Transactional
    public Course create(Course course) {
        Course createdCourse = course;
        return courseRepository.save(createdCourse);
    }

    @Override
    @Transactional
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = CourseNotFoundException.class)
    public Course update(Course course) throws CourseNotFoundException {
        Course updatedCourse = courseRepository.findOne(course.getId());

        if (updatedCourse == null) {
            throw new CourseNotFoundException();
        }

        updatedCourse.setName(course.getName());
        updatedCourse.setPath(course.getPath());
        updatedCourse.setCourseStatus(course.getCourseStatus());

        return updatedCourse;
    }

    @Override
    @Transactional
    public Course findById(long id) {
        return courseRepository.findOne(id);
    }
}
