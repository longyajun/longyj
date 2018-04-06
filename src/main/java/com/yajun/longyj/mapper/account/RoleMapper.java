package com.yajun.longyj.mapper.account;

import org.apache.ibatis.annotations.Param;

import com.yajun.longyj.entity.Resource;
import com.yajun.longyj.entity.Role;
import com.yajun.longyj.entity.RoleResource;
import com.yajun.longyj.entity.UserRole;

import java.util.List;

public interface RoleMapper {
    Integer add(Role role);

    Integer delete(Integer id);

    Integer batchDelete(@Param("ids") List<Integer> ids);

    Role load(Integer id);

    Integer update(Role role);

    List<Role> listRole();

    UserRole loadUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 为单个用户设置单个角色
     * @param userId
     * @param roleId
     * @return
     */
    Integer addUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 为单个用户设置多个角色
     * @param userId
     * @param roleIds
     * @return
     */
    Integer addUserRoles(@Param("userId") int userId,@Param("roleIds")List<Integer> roleIds);

    Integer deleteUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    /**
     * 删除某个用户的所有角色
     * @param uid
     */
    Integer deleteUserRoles(int uid);

    Integer batchDeleteRoleResource(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据角色id获取可以访问的所有资源
     * @param roleId
     * @return
     */
    List<Resource> listRoleResource(int roleId);

    Integer addRoleResource(@Param("roleId") int roleId,@Param("resourceId") int resourceId);

    Integer deleteRoleResource(@Param("roleId")int roleId,@Param("resourceId")int resorceId);

    RoleResource loadResourceRole(@Param("roleId")int roleId,@Param("resourceId") int resorceId);

    Integer deleteRoleAndUser(@Param("ids") List<Integer> ids);
}
