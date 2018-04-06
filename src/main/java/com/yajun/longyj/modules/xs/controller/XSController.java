package com.yajun.longyj.modules.xs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yajun.longyj.model.Article;
import com.yajun.longyj.modules.xs.util.UtilMethod;

/**
 * 10095715
 * 10095714
 * @ClassName: XSController
 * @Description: 
 * @author  LONGYAJUN_LYJ@163.com
 * @date 2018年3月28日 下午4:47:22
 *
 */
@Controller
@RequestMapping("/")
public class XSController {
	
	@RequestMapping(value = "/xs",method = RequestMethod.GET)
	public String list(Model model,Article articles,String id){
	        
		String firstUrl = "";
		if(id == null || id.equals("")){
			firstUrl = "http://www.bxwx9.org/b/59/59047/10095713.html";
		}else{
			firstUrl = null;
			int pint = Integer.parseInt(id);
			System.out.println(pint+1);
			firstUrl = articles.getNextUrl();
		}
		System.out.println(firstUrl);
		Article article = UtilMethod.getArticle(firstUrl);
		model.addAttribute("id", article.getId());//下一页
		model.addAttribute("nexturl", article.getNextUrl());
		model.addAttribute("content", article.getContent());//内容
		
		System.out.println(article.getNextUrl());
        return "xiaoshuo";
    }

}
