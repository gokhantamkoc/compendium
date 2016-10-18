package com.tamkoc.product.compendium.editorsupport;

import com.tamkoc.product.compendium.model.SessionStatus;
import com.tamkoc.product.compendium.service.ISessionStatusService;

import java.beans.PropertyEditorSupport;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
public class SessionStatusEditorSupport extends PropertyEditorSupport {
    ISessionStatusService sessionStatusService;

    public SessionStatusEditorSupport(ISessionStatusService sessionStatusService) {
        this.sessionStatusService = sessionStatusService;
    }

    @Override
    public void setAsText(String id)
    {
        SessionStatus sessionStatus = sessionStatusService.findById(Short.parseShort(id));
        this.setValue(sessionStatus);
    }
}
