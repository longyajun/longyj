package com.yajun.longyj.modules.account.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yajun.longyj.common.util.ShiroKit;
import com.yajun.longyj.entity.Resource;
import com.yajun.longyj.entity.User;
import com.yajun.longyj.modules.account.service.IUserService;

/**
 * 自定义ShiroRealm,实现对自定义数据表的操作
 * @ClassName: ShiroRealm
 * @Description: spring
 * @author  LONGYAJUN_LYJ@163.com
 * @date 2018年3月7日 下午3:47:42
 *
 */
public class ShiroRealm extends AuthorizingRealm {
    
	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
   
	@Autowired
    private IUserService userService;
	// UserService userService = (UserService)InitServlet.getBean("userService");

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("--- MyRealm doGetAuthorizationInfo ---");

        // 获得经过认证的主体信息
        User user = (User)principalCollection.getPrimaryPrincipal();
        Integer userId = user.getId();
        List<Resource> resourceList = userService.listAllResource(userId);
        List<String> roleSnList = userService.listRoleSnByUser(userId);

        List<String> resStrList = new ArrayList<>();
        for(Resource resource:resourceList){
            resStrList.add(resource.getUrl());
        }
        //添加角色和权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<String>(roleSnList));
        info.setStringPermissions(new HashSet<String>(resStrList));

        // 以上完成了动态地对用户授权
        logger.info("role => " + roleSnList);
        logger.info("permission => " + resStrList);

        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return 登录验证
     * @throws AuthenticationException
     * 
     * 
     * 
     * 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("--- MyRealm doGetAuthenticationInfo ---");
        if(authenticationToken == null){
        	return null;
        }
        //获取用户信息
        String username = authenticationToken.getPrincipal().toString();  
        String password = new String((char[]) authenticationToken.getCredentials());  
        String md5Pwd = ShiroKit.md5Pwd(password, username);  
        User user = userService.login(username,md5Pwd);
        if(user != null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
            info.setCredentialsSalt(ByteSource.Util.bytes(username.getBytes()));
            System.out.println("认证之前的数据===============》》"+info);
            return info;
        }
        return null;
    }

//    @Override
//    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
//    	Cache c = getAuthenticationCache();
//    	logger.info("清除【认证】缓存之前"+c);
//        for(Object o : c.keys()){
//            logger.info( o + " , " + c.get(o));
//        }
//        super.clearCachedAuthenticationInfo(principals);
//        logger.info("调用父类清除【认证】缓存之后");
//        for(Object o : c.keys()){
//            logger.info( o + " , " + c.get(o));
//        }
//
//        // 添加下面的代码清空【认证】的缓存
//        User user = (User) principals.getPrimaryPrincipal();
//        SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUsername(),getName());
//        super.clearCachedAuthenticationInfo(spc);
//        logger.info("添加了代码清除【认证】缓存之后");
//        int cacheSize = c.keys().size();
//        logger.info("【认证】缓存的大小:" + c.keys().size());
//        if (cacheSize == 0){
//            logger.info("说明【认证】缓存被清空了。");
//        }
//    }
//
//    @Override
//    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
//        logger.info("清除【授权】缓存之前");
//        Cache c = getAuthorizationCache();//授权缓存
//        for(Object o : c.keys()){
//            logger.info( o + " , " + c.get(o));
//        }
//        super.clearCachedAuthorizationInfo(principals);
//        logger.info("清除【授权】缓存之后");
//        int cacheSize = c.keys().size();
//        logger.info("【授权】缓存的大小:" + cacheSize);
//
//        for(Object o : c.keys()){
//            logger.info( o + " , " + c.get(o));
//        }
//        if(cacheSize == 0){
//            logger.info("说明【授权】缓存被清空了。");
//        }
//    }
}
