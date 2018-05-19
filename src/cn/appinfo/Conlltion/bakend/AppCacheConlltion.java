package cn.appinfo.Conlltion.bakend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.appinfo.Servlet.appVersionServlet;
import cn.appinfo.Servlet.appinfo_Servlet;
import cn.appinfo.entity.AppInfo;
import cn.appinfo.entity.AppVersion;

@Controller
public class AppCacheConlltion {

	@Resource
	appinfo_Servlet appinfo;
	
	@Resource
	appVersionServlet appversion;
	
	@RequestMapping("/AppCacheList")
	private String AppCacheList(HttpServletRequest request) {
		String id = request.getParameter("vid");
		AppInfo Vite = new AppInfo();
		AppVersion Avter = new AppVersion();
		Avter = appversion.AppVersionShow(Integer.parseInt(id));
		Vite = appinfo.AppinfoMax(Integer.parseInt(id));
		request.setAttribute("AppVerSion", Avter);
		request.setAttribute("AppCache", Vite);
		return "/backend/appcheck";
	}
	
	@RequestMapping("/CacheUpdate")
	private String AppCacheUpdate(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);
		String statusid = request.getParameter("status");
		System.out.println(statusid);
		boolean folg = false;
		folg = appinfo.AppinfoUpdate(Integer.parseInt(id),Integer.parseInt(statusid));
		if(folg) {
			return "redirect:/manager/backend/app/list";
		}else {
			return "/AppCacheList";
		}	
	}
}
