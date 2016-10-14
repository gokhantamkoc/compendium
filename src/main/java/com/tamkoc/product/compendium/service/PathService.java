package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.PathNotFoundException;
import com.tamkoc.product.compendium.model.Path;

import com.tamkoc.product.compendium.repository.PathRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 15.10.2016.
 */

@Service
public class PathService implements IPathService {

    @Resource
    PathRepository pathRepository;

    @Override
    @Transactional
    public Path create(Path path) {
        Path createdPath = path;
        return pathRepository.save(createdPath);
    }

    @Override
    @Transactional
    public List<Path> findAll() {
        return pathRepository.findAll();
    }

    @Override
    @Transactional
    public Path update(Path path) throws PathNotFoundException {
        Path updatedPath = pathRepository.findOne(path.getId());
        if(updatedPath == null) {
            throw new PathNotFoundException();
        }

        updatedPath.setPathStatus(path.getPathStatus());
        updatedPath.setDomain(path.getDomain());
        updatedPath.setName(path.getName());
        updatedPath.setDescription(path.getDescription());

        return null;
    }

    @Override
    @Transactional
    public Path findById(long id) {
        return pathRepository.findOne(id);
    }
}
