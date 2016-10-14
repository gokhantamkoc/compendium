package com.tamkoc.product.compendium.editorsupport;

import com.tamkoc.product.compendium.model.DomainStatus;
import com.tamkoc.product.compendium.service.IDomainStatusService;

import java.beans.PropertyEditorSupport;

/**
 * @author Gokhan Tamkoc on 13.10.2016.
 */
public class DomainStatusEditorSupport extends PropertyEditorSupport {

    IDomainStatusService domainStatusService;

    //This will be called when user HTTP Post to server a field bound to DomainStatus

    public DomainStatusEditorSupport(IDomainStatusService domainStatusService) {
        this.domainStatusService = domainStatusService;
    }

    @Override
    public void setAsText(String id)
    {
        DomainStatus domainStatus = domainStatusService.findById(Short.parseShort(id));
        this.setValue(domainStatus);
    }
}
