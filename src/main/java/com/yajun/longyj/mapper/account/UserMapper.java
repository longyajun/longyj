package com.yajun.longyj.mapper.account;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.yajun.longyj.entity.Resource;
import com.yajun.longyj.entity.Role;
import com.yajun.longyj.entity.User;

import java.util.List;

public interface UserMapper {
    
	Integer add(User user);

    Integer update(User user);

    Integer delete(Integer id);

    Integer batchDelete(@Param("ids") List<Integer> ids);

    User load(Integer id);

    List<User> listUser();

    User loadByUserName(String username);

    /**
     * 根据角色 id 查询所有是该角色的用户列表
     * @param rid
     * @return
     */
    List<User> listByRole(Integer rid);

    List<Resource> listAllResources(Integer uid);

    List<String> listRoleSnByUser(Integer uid);

    List<Role> listUserRole(Integer uid);
    
    /**
     * 分页
     * @Title: findByPage
     * @Description: 
     * @return Page<Person>
     * @author  LONGYAJUN_LYJ@163.com
     * @date 2018年3月21日 下午4:00:32
     */
    Page<User> findByPage();
}
