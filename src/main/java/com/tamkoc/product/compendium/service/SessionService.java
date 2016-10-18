package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.SessionNotFoundException;
import com.tamkoc.product.compendium.model.Session;
import com.tamkoc.product.compendium.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
@Service
public class SessionService implements ISessionService {

    @Resource
    SessionRepository sessionRepository;

    @Override
    @Transactional
    public Session create(Session session) {
        Session createdSession = session;
        return sessionRepository.save(createdSession);
    }

    @Override
    @Transactional
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = SessionNotFoundException.class)
    public Session update(Session session) throws SessionNotFoundException {
        Session updatedSession = sessionRepository.findOne(session.getId());
        if(updatedSession == null) {
            throw new SessionNotFoundException();
        }

        updatedSession.setOrderNumber(session.getOrderNumber());
        updatedSession.setName(session.getName());
        updatedSession.setBody(session.getBody());
        updatedSession.setSummary(session.getSummary());
        updatedSession.setCourse(session.getCourse());
        updatedSession.setSessionStatus(session.getSessionStatus());

        return updatedSession;
    }

    @Override
    @Transactional
    public Session findById(long id) {
        return sessionRepository.findOne(id);
    }
}
