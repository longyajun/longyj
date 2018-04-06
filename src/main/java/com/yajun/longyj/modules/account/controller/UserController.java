package com.yajun.longyj.modules.account.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.yajun.longyj.common.util.FileType;
import com.yajun.longyj.common.util.JsonOutPut;
import com.yajun.longyj.entity.Resource;
import com.yajun.longyj.entity.Role;
import com.yajun.longyj.entity.User;
import com.yajun.longyj.model.PageInfo;
import com.yajun.longyj.modules.account.service.IRoleService;
import com.yajun.longyj.modules.account.service.IUserService;

/**
 * 1331.18
 * 324.48
 * 登录用户的个人信息
 * @ClassName: UserController
 * @Description: 
 * @author  LONGYAJUN_LYJ@163.com
 * @date 2018年3月7日 上午10:18:27
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model model){
        model.addAttribute("list",userService.list());
        return "admin/user/list";
    }
	
	 /**
     * 跳转到添加用户的页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        logger.debug("跳转到添加用户的页面");
        model.addAttribute("user",new User());
        model.addAttribute("roles",roleService.list());
        return "admin/user/add";
    }

    /**
     * 添加用户保存的方法
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(User user, HttpServletRequest request){
        logger.debug("添加用户 post 方法");
        logger.debug(user.toString());
        List<Integer> roleIdList = new ArrayList<>();
        String[] roldIds = request.getParameterValues("roleId");
        for(String roleId:roldIds){
            roleIdList.add(Integer.parseInt(roleId));
        }
        userService.add(user,roleIdList);
        // 重定向到本 Controller 的 list 方法（Get 方式）
        return "redirect:list";
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    public Map<String,Object> updateStatus(User user){
        User u = userService.update(user);
        Map<String,Object> result = new HashMap<>();
        if(u != null){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("errorInfo","更新状态失败");
        }
        return result;
    }

    /**
     * 跳转到用户信息更新页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id,Model model){
        // 要从数据库查询对象进行回显
        User user = userService.load(id);
        model.addAttribute("user",user);
        // 所有的角色列表
        model.addAttribute("roles",roleService.list());

        /**
         * 根据用户 id 查询用户的所有角色
         */
        List<Role> hasRoles = userService.listUserRole(id);
        /**
         * 将用户的所有角色 id 添加到一个字符串中
         */
        List<Integer> rids = new ArrayList<>();
        for(Role r:hasRoles) {
            rids.add(r.getId());
        }
        // 指定用户拥有的角色信息
        model.addAttribute("hasRole", rids);
        return "admin/user/update";
    }

    /**
     * 更新用户的信息（包括更新用户绑定的角色）
     * @param user
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(User user,HttpServletRequest request){
        // // TODO: 2016/9/18 这个过程还是可以优化的，如果属性没有发生变化的地方，是不须要更新的
        logger.debug("user => " + user);
        String[] roleIds = request.getParameterValues("roleId");
        List<Integer> roleIdList = new ArrayList<>();
        for(String roleId:roleIds){
            roleIdList.add(Integer.valueOf(roleId));
        }
        userService.update(user,roleIdList);
        return "redirect:/admin/user/list";
    }

    /**
     *  根据用户 id 跳转到用户权限的列表页面
     * @return
     */
    @RequestMapping(value = "/resources/{id}",method = RequestMethod.GET)
    public String listResources(@PathVariable("id") Integer userId,Model model){
        List<Resource> resourceList = userService.listAllResource(userId);
        User user = userService.load(userId);
        model.addAttribute("resources",resourceList);
        model.addAttribute("user",user);
        return "admin/user/resources";
    }

    /**
     * 批量删除用户
     * 1、删除用户数据
     * 2、删除用户绑定的角色数据
     * @param userIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestParam("userIds[]") List<Integer> userIds){
        Map<String,Object> result = new HashMap<>();
        try{
            userService.deleteUserAndRole(userIds);
            result.put("success",true);
        }catch (RuntimeException e){
            e.printStackTrace();
            result.put("success",false);
            result.put("errorInfo",e.getMessage());
        }
        return result;
    }

    /**
     * 分页
     * @Title: list
     * @Description: 
     * @param model
     * @return String
     * @author  LONGYAJUN_LYJ@163.com
     * @date 2018年3月21日 下午4:40:08
     */
    @RequestMapping(value = "/findByPageList/{id}",method = RequestMethod.GET)
	public String findByPageList(int pageId){
    	Map<String,Object> result = new HashMap<>();
    	Page<User> persons = userService.findByPage(2, 2);
		PageInfo<User> pageInfo = new PageInfo<>(persons);
		System.out.println(JSON.toJSONString(pageInfo));
		System.out.println(pageInfo.toString());
		result.put("msg", JSON.toJSONString(pageInfo));
        return "admin/user/list";
    }
    
    //导入
    @RequestMapping(value = "/batchImport",method = RequestMethod.POST)
    public String batchImportUserKnowledge(@RequestParam(value="filename") MultipartFile file,
    		HttpSession session){
    	if(file == null){
    		session.setAttribute("msg","文件不能为空！");  
    	    return "redirect:toUserKnowledgeImport";  
    	}
    	//获取文件名  
        String fileName=file.getOriginalFilename();  
          
        //验证文件名是否合格  
        if(!FileType.validateExcel(fileName)){  
         session.setAttribute("msg","文件必须是excel格式！");  
         return "redirect:toUserKnowledgeImport";  
        }  
          
        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）  
        long size=file.getSize();  
        if(StringUtils.isEmpty(fileName) || size==0){  
         session.setAttribute("msg","文件不能为空！");  
         return "redirect:toUserKnowledgeImport";  
        }  
          
        //批量导入  
//        String message = userService.batchImport(fileName,file,account.getUsername());  
//        session.setAttribute("msg",message);  
        return "redirect:toUserKnowledgeImport";  
    }
	
}





























