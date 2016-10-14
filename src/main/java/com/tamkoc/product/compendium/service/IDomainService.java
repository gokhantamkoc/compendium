package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.DomainNotFoundException;
import com.tamkoc.product.compendium.model.Domain;

/**
 * @author Gokhan Tamkoc on 08.10.2016.
 */
public interface IDomainService {
    public Domain create(Domain domain);
    public java.util.List<Domain> findAll();
    public Domain update(Domain domain) throws DomainNotFoundException;
    public Domain findById(long id);
}
