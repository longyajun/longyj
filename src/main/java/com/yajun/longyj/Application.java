package com.yajun.longyj;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * 启动类
 * http://localhost:8080/feed/user?id=2
 * @ClassName: Application
 * @Description: 
 * @author  LONGYAJUN_LYJ@163.com
 * @date 2018年1月18日 下午2:03:25
 *
 */
@SpringBootApplication
@MapperScan("com.yajun.longyj.mapper.*")
public class Application extends SpringBootServletInitializer{
	//SpringBootServletInitializer
    
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	/**1、 extends WebMvcConfigurationSupport
	 * 2、重写下面方法;
	 * setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配；
	 * setUseTrailingSlashMatch : 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
	 */
//	@Override
//	public void configurePathMatch(PathMatchConfigurer configurer) {
//		configurer.setUseSuffixPatternMatch(true).setUseTrailingSlashMatch(false);
//	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("SpringBoot Start Success");
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    SpringBoot启动成功      ヾ(◍°∇°◍)ﾉﾞ");
    }
    
}

