package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.PathStatusNotFoundException;
import com.tamkoc.product.compendium.model.PathStatus;

import java.util.List;

/**
 * @author Gokhan Tamkoc on 15.10.2016.
 */
public interface IPathStatusService {
    public PathStatus create(PathStatus path);
    public PathStatus delete(short id) throws PathStatusNotFoundException;
    public List<PathStatus> findAll();
    public PathStatus update(PathStatus pathStatus) throws PathStatusNotFoundException;
    public PathStatus findById(short id);
}
