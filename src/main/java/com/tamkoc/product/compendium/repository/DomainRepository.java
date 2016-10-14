package com.tamkoc.product.compendium.repository;

import com.tamkoc.product.compendium.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gokhan Tamkoc on 08.10.2016.
 */
public interface DomainRepository extends JpaRepository<Domain, Long> {
}
