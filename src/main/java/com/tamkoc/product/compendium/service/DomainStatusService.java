package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.DomainStatusNotFoundException;
import com.tamkoc.product.compendium.model.DomainStatus;
import com.tamkoc.product.compendium.repository.DomainStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 13.10.2016.
 */
@Service
public class DomainStatusService implements IDomainStatusService {

    @Resource
    DomainStatusRepository domainStatusRepository;

    @Override
    @Transactional
    public DomainStatus create(DomainStatus domainStatus) {
        DomainStatus createdDomainStatus = domainStatus;
        return domainStatusRepository.save(createdDomainStatus);
    }

    @Override
    @Transactional
    public List<DomainStatus> findAll() {
        return domainStatusRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = DomainStatusNotFoundException.class)
    public DomainStatus update(DomainStatus domainStatus) throws DomainStatusNotFoundException {
        DomainStatus updatedDomainStatus = domainStatusRepository.findOne(domainStatus.getId());

        if(updatedDomainStatus == null) {
            throw new DomainStatusNotFoundException();
        }

        updatedDomainStatus.setDescription(domainStatus.getDescription());

        return updatedDomainStatus;
    }

    @Override
    @Transactional
    public DomainStatus findById(short id) {
        return domainStatusRepository.findOne(id);
    }
}
