package cn.sunline.tiny;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sunline.tiny.controller.BaseController;

@Controller
public class AppController  extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

	@RequestMapping(path = "/{tranCode}.tml")
	public void index(HttpServletRequest request, HttpServletResponse response) {
		
		LOG.debug("start...");
		doPost(request, response);
	}
	
	@RequestMapping(path = "/{tranCode}.html")
	public void index_html(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("start...");
		doPost(request, response);
	}
}

