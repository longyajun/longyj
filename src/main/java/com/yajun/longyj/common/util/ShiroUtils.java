package com.yajun.longyj.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.yajun.longyj.entity.User;

public class ShiroUtils {
	
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}

	public static User getUser() {
		return (User) getSubjct().getPrincipal();
	}

	public static Integer getUserId() {
		return getUser().getId();
	}

	public static void logout() {
		getSubjct().logout();
	}
}
