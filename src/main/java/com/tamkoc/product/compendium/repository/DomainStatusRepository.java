package com.tamkoc.product.compendium.repository;

import com.tamkoc.product.compendium.model.DomainStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gokhan Tamkoc on 13.10.2016.
 */
public interface DomainStatusRepository extends JpaRepository<DomainStatus, Short> {
}
