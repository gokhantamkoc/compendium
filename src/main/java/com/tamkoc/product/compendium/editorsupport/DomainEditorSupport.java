package com.tamkoc.product.compendium.editorsupport;

import com.tamkoc.product.compendium.model.Domain;
import com.tamkoc.product.compendium.service.IDomainService;

import java.beans.PropertyEditorSupport;

/**
 * @author Gokhan Tamkoc on 15.10.2016.
 */
public class DomainEditorSupport extends PropertyEditorSupport {
    IDomainService domainService;

    //This will be called when user HTTP Post to server a field bound to DomainStatus

    public DomainEditorSupport(IDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public void setAsText(String id)
    {
        Domain domain = domainService.findById(Long.parseLong(id));
        this.setValue(domain);
    }
}
