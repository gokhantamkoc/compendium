package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.SessionStatusNotFoundException;
import com.tamkoc.product.compendium.model.SessionStatus;

import java.util.List;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
public interface ISessionStatusService {
    public SessionStatus create(SessionStatus sessionStatus);
    public List<SessionStatus> findAll();
    public SessionStatus update(SessionStatus sessionStatus) throws SessionStatusNotFoundException;
    public SessionStatus findById(short id);
}
