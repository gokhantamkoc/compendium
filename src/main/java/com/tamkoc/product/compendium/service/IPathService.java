package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.PathNotFoundException;
import com.tamkoc.product.compendium.model.Path;

/**
 * @author Gokhan Tamkoc on 15.10.2016.
 */
public interface IPathService {
        public Path create(Path path);
        public java.util.List<Path> findAll();
        public Path update(Path path) throws PathNotFoundException;
        public Path findById(long id);
}
