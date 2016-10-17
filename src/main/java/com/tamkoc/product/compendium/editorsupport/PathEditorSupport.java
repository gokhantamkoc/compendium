package com.tamkoc.product.compendium.editorsupport;

import com.tamkoc.product.compendium.model.Path;
import com.tamkoc.product.compendium.service.IPathService;

import java.beans.PropertyEditorSupport;

/**
 * @author Gokhan Tamkoc on 17.10.2016.
 */
public class PathEditorSupport extends PropertyEditorSupport{
    IPathService pathService;

    public PathEditorSupport(IPathService pathService) {
        this.pathService = pathService;
    }

    @Override
    public void setAsText(String id)
    {
        Path path = pathService.findById(Long.parseLong(id));
        this.setValue(path);
    }
}
