package com.yajun.longyj.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yajun.longyj.entity.Resource;
import com.yajun.longyj.entity.Role;
import com.yajun.longyj.entity.RoleResource;
import com.yajun.longyj.entity.UserRole;
import com.yajun.longyj.mapper.account.RoleMapper;
import com.yajun.longyj.modules.account.service.IRoleService;



@Service
public class RoleService implements IRoleService {
    
	@Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer add(Role role) {
        return roleMapper.add(role);
    }

    @Override
    public Integer delete(int id) {
        return roleMapper.delete(id);
    }

    @Transactional
    @Override
    public void deleteRoleAndResource(List<Integer> ids) {
        roleMapper.batchDelete(ids);
        roleMapper.batchDeleteRoleResource(ids);
    }

    @Override
    public Role load(int id) {
        return roleMapper.load(id);
    }

    @Override
    public List<Role> list() {
        return roleMapper.listRole();
    }

    @Override
    public Integer update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public List<Role> listRole() {
        return roleMapper.listRole();
    }

    @Override
    public UserRole loadUserRole(int uid, int roleId) {
        return roleMapper.loadUserRole(uid, roleId);
    }

    @Override
    public void addUserRole(int uid, int roleId) {
        roleMapper.addUserRole(uid, roleId);
    }

    @Override
    public void deleteUserRole(int uid, int roleId) {
        roleMapper.deleteUserRole(uid, roleId);
    }

    @Override
    public void deleteUserRoles(int uid) {
        roleMapper.deleteUserRoles(uid);
    }

    @Override
    public List<Resource> listRoleResource(int roleId) {
        return roleMapper.listRoleResource(roleId);
    }

    @Override
    public void addRoleResource(int roleId, int resId) {
        roleMapper.addRoleResource(roleId, resId);
    }

    @Override
    public void deleteRoleResource(int roleId, int resId) {
        roleMapper.deleteRoleResource(roleId, resId);
    }

    @Override
    public RoleResource loadResourceRole(int roleId, int resId) {
        return roleMapper.loadResourceRole(roleId, resId);
    }

    @Override
    public Integer deleteRoleAndUser(List<Integer> ids) {
        return roleMapper.deleteRoleAndUser(ids);
    }
}
