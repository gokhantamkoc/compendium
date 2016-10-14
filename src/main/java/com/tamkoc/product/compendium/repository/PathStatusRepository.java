package com.tamkoc.product.compendium.repository;

import com.tamkoc.product.compendium.model.PathStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gokhan Tamkoc on 15.10.2016.
 */
public interface PathStatusRepository extends JpaRepository<PathStatus, Short> {
}
