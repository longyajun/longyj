package com.yajun.longyj.modules.account.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.yajun.longyj.common.util.AjaxJson;
import com.yajun.longyj.common.util.BeautifyUtil;
import com.yajun.longyj.common.util.JsonOutPut;
import com.yajun.longyj.common.util.ShiroKit;
import com.yajun.longyj.common.util.ShiroUtils;
import com.yajun.longyj.entity.User;
import com.yajun.longyj.model.PageInfo;
import com.yajun.longyj.modules.account.service.IUserService;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;
	
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletResponse response){
//    	System.out.println("进入登录页面！！！");
//    	User user=userService.loadByUsername("test");
//        String userStr= JSON.toJSONString(user);
//        
//        logger.info("打印：{0}" + BeautifyUtil.formatJson(userStr));
//        JsonOutPut.objectToResponse(new AjaxJson(0,userStr.toString()), response);

        return "login";
    }
    
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, Model model){
        String username = user.getUsername();
        String password = user.getPassword();
        logger.info("准备登录用户 => " + username);
        logger.info("准备用户密码 => " + password);
        String md5Pwd = ShiroKit.md5Pwd(password, username);

        // 1、获取Subject实例对象
        Subject subject = SecurityUtils.getSubject();
        // 2、判断当前用户是否登录
        logger.info("用户是否授权 => " + subject.isAuthenticated());
        if(subject.isAuthenticated() == false){
        	//3、将用户名和密码封装到UsernamePasswordToken
        	UsernamePasswordToken token = new UsernamePasswordToken(username,md5Pwd);
            String msg = null;
            //4、认证
            try {
            	logger.info("对用户[" + username + "]进行登录验证..验证开始");
            	subject.login(token);//传到MyAuthorizingRealm类中的方法进行认证
            	logger.info("对用户[" + username + "]进行登录验证..验证通过");
            } catch (UnknownAccountException e) {
            	logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
                e.printStackTrace();
                msg = e.getMessage()+" ,未知账户";
            } catch (IncorrectCredentialsException e){
            	logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            	e.printStackTrace();
            	System.out.println(e.getMessage());
                msg = "用户名和密码的组合不正确";//密码不匹配(生产环境中应该写:用户名和密码的组合不正确)
            } catch (LockedAccountException e){
            	logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            	msg="账户已锁定";
                e.printStackTrace();
                msg = e.getMessage();
            }catch(ExcessiveAttemptsException e){
            	 logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
                 msg="用户名或密码错误次数过多";
            }catch (AuthenticationException ae) {
                //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
                logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
                ae.printStackTrace();
                msg = "用户名或密码不正确";
            }
            if(msg == null){
//              return "redirect:/admin/user/list";
              return "redirect:/admin/user/list";
          }
            model.addAttribute("msg",msg);
        }else{
        	if(subject.isAuthenticated() == true){
        		ShiroUtils.logout();
        		return login(user,model);
        	}
        }
        System.out.println(model);
        return "login";
    }
    
    @RequestMapping(value = "/logout1",method = RequestMethod.GET)
    public String logout(Model model){
        ShiroUtils.logout();
        model.addAttribute("msg","您已经退出登录");
        return "login";
    }
}