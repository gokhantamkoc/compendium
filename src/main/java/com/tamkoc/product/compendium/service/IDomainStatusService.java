package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.DomainStatusNotFoundException;
import com.tamkoc.product.compendium.model.DomainStatus;

import java.util.List;

/**
 * @author Gokhan Tamkoc on 13.10.2016.
 */
public interface IDomainStatusService {
    public DomainStatus create(DomainStatus domainStatus);
    public List<DomainStatus> findAll();
    public DomainStatus update(DomainStatus domainStatus) throws DomainStatusNotFoundException;
    public DomainStatus findById(short id);
}
