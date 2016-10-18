package com.tamkoc.product.compendium.service;

import com.tamkoc.product.compendium.exception.PathStatusNotFoundException;
import com.tamkoc.product.compendium.model.PathStatus;
import com.tamkoc.product.compendium.repository.PathStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 15.10.2016.
 */

@Service
public class PathStatusService implements IPathStatusService{

    @Resource
    PathStatusRepository pathStatusRepository;

    @Override
    @Transactional
    public PathStatus create(PathStatus pathStatus) {
        return null;
    }

    @Override
    @Transactional
    public PathStatus delete(short id) throws PathStatusNotFoundException {
        PathStatus deletedPathStatus = pathStatusRepository.findOne(id);

        if(deletedPathStatus == null){
            throw new PathStatusNotFoundException();
        }

        pathStatusRepository.delete(id);

        return deletedPathStatus;
    }

    @Override
    public List<PathStatus> findAll() {
        return pathStatusRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = PathStatusNotFoundException.class)
    public PathStatus update(PathStatus pathStatus) throws PathStatusNotFoundException {
        PathStatus updatedPathStatus = pathStatusRepository.findOne(pathStatus.getId());

        if(updatedPathStatus == null) {
            throw new PathStatusNotFoundException();
        }

        updatedPathStatus.setDescription(pathStatus.getDescription());

        return updatedPathStatus;
    }

    @Override
    @Transactional
    public PathStatus findById(short id) {
        return pathStatusRepository.findOne(id);
    }
}
