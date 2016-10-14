package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.DomainStatusNotFoundException;
import com.tamkoc.product.compendium.model.DomainStatus;
import com.tamkoc.product.compendium.repository.DomainStatusRepository;
import org.hibernate.type.ShortType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gokhan Tamkoc on 13.10.2016.
 */
@Service
public class DomainStatusService implements IDomainStatusService {

    @Resource
    DomainStatusRepository domainStatusRepository;

    @Override
    public DomainStatus create(DomainStatus domainStatus) {
        return null;
    }

    @Override
    public DomainStatus delete(short id) throws DomainStatusNotFoundException {
        return null;
    }

    @Override
    public List<DomainStatus> findAll() {
        return domainStatusRepository.findAll();
    }

    @Override
    public DomainStatus update(DomainStatus domainStatus) throws DomainStatusNotFoundException {
        return null;
    }

    @Override
    public DomainStatus findById(short id) {
        return domainStatusRepository.findOne(id);
    }
}
