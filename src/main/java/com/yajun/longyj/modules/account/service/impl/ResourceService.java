package com.yajun.longyj.modules.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yajun.longyj.entity.Resource;
import com.yajun.longyj.mapper.account.ResourceMapper;
import com.yajun.longyj.modules.account.service.IResourceService;

import java.util.List;

@Service
public class ResourceService implements IResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Integer add(Resource res) {
        return resourceMapper.add(res);
    }

    @Override
    public Integer update(Resource res) {
        return resourceMapper.update(res);
    }

    @Override
    public Integer delete(int id) {
        return resourceMapper.delete(id);
    }

    @Override
    public Resource load(int id) {
        return resourceMapper.load(id);
    }

    @Override
    public List<Resource> listResource() {
        return resourceMapper.listResource();
    }
}
