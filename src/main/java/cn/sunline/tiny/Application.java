package cn.sunline.tiny;
import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.WebApplicationContext;

import cn.sunline.tiny.core.BeanFactory;
import cn.sunline.tiny.core.InitTiny;
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "cn.sunline" ,"flow"})
public class Application extends SpringBootServletInitializer {

	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
		InitTiny.init(servletContext);
		WebApplicationContext context = super.createRootApplicationContext(servletContext);
		BeanFactory.initByTinyServer2(context);

		return context;
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		InitTiny.init();
		ApplicationContext ctx = (ApplicationContext) SpringApplication.run(Application.class, args);
		BeanFactory.initByTinyServer2(ctx);
	}

}

