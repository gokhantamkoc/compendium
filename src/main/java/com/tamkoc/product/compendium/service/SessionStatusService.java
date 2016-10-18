package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.SessionStatusNotFoundException;
import com.tamkoc.product.compendium.model.SessionStatus;
import com.tamkoc.product.compendium.repository.SessionStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
@Service
public class SessionStatusService implements ISessionStatusService {
    @Resource
    SessionStatusRepository sessionStatusRepository;

    @Override
    @Transactional
    public SessionStatus create(SessionStatus sessionStatus) {
        SessionStatus createdSessionStatus = sessionStatus;
        return sessionStatusRepository.save(createdSessionStatus);
    }

    @Override
    @Transactional
    public List<SessionStatus> findAll() {
        return sessionStatusRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = SessionStatusNotFoundException.class)
    public SessionStatus update(SessionStatus sessionStatus) throws SessionStatusNotFoundException {
        SessionStatus updatedSessionStatus = sessionStatusRepository.findOne(sessionStatus.getId());
        if(updatedSessionStatus == null) {
            throw new SessionStatusNotFoundException();
        }

        updatedSessionStatus.setDescription(sessionStatus.getDescription());

        return updatedSessionStatus;
    }

    @Override
    @Transactional
    public SessionStatus findById(short id) {
        return sessionStatusRepository.findOne(id);
    }
}
