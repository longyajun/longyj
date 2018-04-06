package com.yajun.longyj.modules.account.service;


import java.util.List;

import com.yajun.longyj.entity.Resource;

public interface IResourceService {
    Integer add(Resource res);

    Integer update(Resource res);

    Integer delete(int id);

    Resource load(int id);

    List<Resource> listResource();
}
