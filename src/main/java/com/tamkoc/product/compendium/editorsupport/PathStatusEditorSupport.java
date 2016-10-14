package com.tamkoc.product.compendium.editorsupport;

import com.tamkoc.product.compendium.model.PathStatus;
import com.tamkoc.product.compendium.service.IPathStatusService;

import java.beans.PropertyEditorSupport;

/**
 * @author Gokhan Tamkoc on 15.10.2016.
 */
public class PathStatusEditorSupport extends PropertyEditorSupport {
    IPathStatusService pathStatusService;

    public PathStatusEditorSupport(IPathStatusService pathStatusService) {
        this.pathStatusService = pathStatusService;
    }

    @Override
    public void setAsText(String id)
    {
        PathStatus pathStatus = pathStatusService.findById(Short.parseShort(id));
        this.setValue(pathStatus);
    }
}
