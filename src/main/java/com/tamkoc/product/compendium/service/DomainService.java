package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.DomainNotFoundException;
import com.tamkoc.product.compendium.exception.DomainStatusNotFoundException;
import com.tamkoc.product.compendium.model.Domain;
import com.tamkoc.product.compendium.repository.DomainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 08.10.2016.
 */
@Service
public class DomainService implements IDomainService {

    @Resource
    DomainRepository domainRepository;

    @Override
    @Transactional
    public Domain create(Domain domain) {
        Domain createdDomain = domain;
        return domainRepository.save(createdDomain);
    }

    @Override
    @Transactional
    public List<Domain> findAll() {
        return domainRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor=DomainNotFoundException.class)
    public Domain update(Domain domain) throws DomainNotFoundException {
        Domain updatedDomain = domainRepository.findOne(domain.getId());

        if (updatedDomain == null) {
            throw new DomainNotFoundException();
        }

        updatedDomain.setName(domain.getName());
        updatedDomain.setDescription(domain.getDescription());
        updatedDomain.setDomainStatus(domain.getDomainStatus());

        return updatedDomain;
    }

    @Override
    public Domain findById(long id) {
        return domainRepository.findOne(id);
    }
}
