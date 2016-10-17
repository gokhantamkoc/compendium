package com.tamkoc.product.compendium.repository;

import com.tamkoc.product.compendium.model.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
public interface CourseStatusRepository extends JpaRepository<CourseStatus, Short> {
}
