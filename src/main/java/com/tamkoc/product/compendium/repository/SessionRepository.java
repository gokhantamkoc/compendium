package com.tamkoc.product.compendium.repository;

import com.tamkoc.product.compendium.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
public interface SessionRepository extends JpaRepository<Session, Long> {
}
