package com.yajun.longyj.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class JsonOutPut {

	private static Logger loger = LoggerFactory.getLogger(JsonOutPut.class);

	/**
	 * 将jsonStr返回到Response对象
	 * 
	 * @Title: objectToResponse
	 * @Description:
	 * @param obj
	 * @param response
	 *            void
	 * @author LONGYAJUN_LYJ@163.com
	 * @date 2018年3月14日 上午11:51:16
	 */
	public static void objectToResponse(Object obj ,HttpServletResponse response) {

		response.setHeader("Content-type", "application/json;charset=utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		Gson gson = new Gson();
		try {
			out = response.getWriter();
			String str = gson.toJson(obj);
			out.print(BeautifyUtil.formatJson(str));
		} catch (Exception e) {
			loger.info("获取PrintWriter对象失败", e);
		} finally {
			out.close();
			out.flush();
		}
	}

	public static void writeJsonResponse(String jsonStr,HttpServletResponse response) {
		try {
			response.setContentType("text/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			loger.info(e.getMessage(), e);
		} catch (Exception e) {
			loger.info(e.getMessage(), e);
		}
	}

}
