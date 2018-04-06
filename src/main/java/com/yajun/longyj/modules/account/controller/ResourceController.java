package com.yajun.longyj.modules.account.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yajun.longyj.entity.Resource;
import com.yajun.longyj.modules.account.service.IResourceService;


@RequestMapping("/admin/resource")
@Controller
public class ResourceController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    private IResourceService resourceService;

    /**
     * 返回到列表显示页面
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        // 查询到所有的权限列表
        List<Resource> resourceList = resourceService.listResource();
        model.addAttribute("resourceList",resourceList);
        return "admin/resource/list";
    }

    /**
     * 跳转到添加权限的页面
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        Resource resource = new Resource();
        model.addAttribute("resource",resource);
        return "admin/resource/add";
    }

    /**
     * 跳转到添加权限的页面
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Resource resource){
        logger.debug(resource.toString());
        resourceService.add(resource);
        return "redirect:list";
    }

    /**
     * 跳转到更新权限的页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id")Integer id,Model model){
        Resource resource = resourceService.load(id);
        model.addAttribute("resource",resource);
        return "admin/resource/update";
    }

    /**
     * 更新权限的方法
     * @param resource
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public String update(Resource resource){
        logger.debug(resource.toString());
        resourceService.update(resource);
        return "redirect:/admin/resource/list";
    }
}
