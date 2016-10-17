package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.editorsupport.CourseStatusNotFoundException;
import com.tamkoc.product.compendium.model.CourseStatus;

import java.util.List;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
public interface ICourseStatusService {
    public CourseStatus create(CourseStatus courseStatus);
    public List<CourseStatus> findAll();
    public CourseStatus update(CourseStatus courseStatus) throws CourseStatusNotFoundException;
    public CourseStatus findById(short id);
}
