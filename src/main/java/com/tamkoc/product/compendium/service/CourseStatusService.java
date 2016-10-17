package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.editorsupport.CourseStatusNotFoundException;
import com.tamkoc.product.compendium.model.CourseStatus;
import com.tamkoc.product.compendium.repository.CourseStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
@Service
public class CourseStatusService implements ICourseStatusService {
    @Resource
    CourseStatusRepository courseStatusRepository;

    @Override
    @Transactional
    public CourseStatus create(CourseStatus courseStatus) {
        return null;
    }

    @Override
    public List<CourseStatus> findAll() {
        return courseStatusRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor=CourseStatusNotFoundException.class)
    public CourseStatus update(CourseStatus courseStatus) throws CourseStatusNotFoundException {
        CourseStatus updatedCourseStatus = courseStatusRepository.findOne(courseStatus.getId());

        if(updatedCourseStatus == null) {
            throw new CourseStatusNotFoundException();
        }

        updatedCourseStatus.setDescription(courseStatus.getDescription());

        return updatedCourseStatus;
    }

    @Override
    public CourseStatus findById(short id) {
        return courseStatusRepository.findOne(id);
    }
}
