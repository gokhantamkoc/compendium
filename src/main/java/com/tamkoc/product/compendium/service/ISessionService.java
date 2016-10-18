package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.SessionNotFoundException;
import com.tamkoc.product.compendium.model.Session;

import java.util.List;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
public interface ISessionService {
    public Session create(Session session);
    public List<Session> findAll();
    public Session update(Session session) throws SessionNotFoundException;
    public Session findById(long id);
}
