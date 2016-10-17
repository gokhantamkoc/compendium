package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.CourseNotFoundException;
import com.tamkoc.product.compendium.model.Course;

import java.util.List;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
public interface ICourseService {
    public Course create(Course course);
    public List<Course> findAll();
    public Course update(Course course) throws CourseNotFoundException;
    public Course findById(long id);
}
