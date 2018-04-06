package com.yajun.longyj.modules.xs;

import com.yajun.longyj.model.Article;
import com.yajun.longyj.modules.xs.util.UtilMethod;


public class XiaoShuo {
	
	public static void main(String[] args) {
//		https://www.bxwx9.org/text/59/59047.html
		String firstUrl = "http://www.bxwx9.org/b/59/59047/10076884.html";
		Article article = UtilMethod.getArticle(firstUrl);
//		System.out.println(article.getTitle());
//		System.out.println(article.getContent());
		while(article.getNextUrl() != null && article.getContent() != null && !article.getId().equals("22204457")){
			article = UtilMethod.getArticle(article.getNextUrl());
			System.out.println(article.getId());
			System.out.println(article.getTitle());
			System.out.println(article.getContent());
		}
	}
}
