package com.yajun.longyj.mapper.account;

import java.util.List;

import com.yajun.longyj.entity.Resource;

public interface ResourceMapper {
	
    Integer add(Resource res);

    Integer update(Resource res);

    Integer delete(int id);

    Resource load(int id);

    List<Resource> listResource();
}
