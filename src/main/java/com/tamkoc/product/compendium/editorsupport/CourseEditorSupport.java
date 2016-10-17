package com.tamkoc.product.compendium.editorsupport;

import com.tamkoc.product.compendium.model.Course;
import com.tamkoc.product.compendium.service.ICourseService;

import java.beans.PropertyEditorSupport;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
public class CourseEditorSupport extends PropertyEditorSupport {
    ICourseService courseService;

    public CourseEditorSupport(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void setAsText(String id)
    {
        Course course = courseService.findById(Long.parseLong(id));
        this.setValue(course);
    }
}
