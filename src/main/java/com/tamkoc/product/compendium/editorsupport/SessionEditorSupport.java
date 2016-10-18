package com.tamkoc.product.compendium.editorsupport;

import com.tamkoc.product.compendium.model.Session;
import com.tamkoc.product.compendium.service.ISessionService;

import java.beans.PropertyEditorSupport;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
public class SessionEditorSupport extends PropertyEditorSupport {
    ISessionService sessionService;

    public SessionEditorSupport(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void setAsText(String id)
    {
        Session session = sessionService.findById(Long.parseLong(id));
        this.setValue(session);
    }
}
