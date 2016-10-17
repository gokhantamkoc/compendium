package com.tamkoc.product.compendium.editorsupport;

import com.tamkoc.product.compendium.model.CourseStatus;
import com.tamkoc.product.compendium.service.ICourseStatusService;

import java.beans.PropertyEditorSupport;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
public class CourseStatusEditorSupport extends PropertyEditorSupport {
    ICourseStatusService courseStatusService;

    public CourseStatusEditorSupport(ICourseStatusService courseStatusService) {
        this.courseStatusService = courseStatusService;
    }

    @Override
    public void setAsText(String id)
    {
        CourseStatus courseStatus = courseStatusService.findById(Short.parseShort(id));
        this.setValue(courseStatus);
    }
}
