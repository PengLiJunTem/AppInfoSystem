package cn.appinfo.Conlltion.developer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;

import cn.appinfo.Servlet.appCateForyServlet;
import cn.appinfo.Servlet.appVersionServlet;
import cn.appinfo.Servlet.appinfo_Servlet;
import cn.appinfo.Servlet.dataDictionServlet;
import cn.appinfo.entity.AppCategory;
import cn.appinfo.entity.AppInfo;
import cn.appinfo.entity.AppVersion;
import cn.appinfo.entity.DataDictionary;

@Controller
public class AppinfoModifyConlltion {

	@Resource
	appinfo_Servlet appinfoServlet;
	
	@Resource
	dataDictionServlet dataDiction;
	
	@Resource
	appCateForyServlet appCateForty;
	
	@Resource
	appVersionServlet appversion;
	
	@RequestMapping("/appinfomodify")
	private String AppinfoUpdate(HttpServletRequest request) {
		String id = request.getParameter("id");
		AppInfo Viet = new AppInfo();
		Viet = appinfoServlet.AppinfoViet(Integer.parseInt(id));
		request.setAttribute("appInfo", Viet);
		return "developer/appinfomodify";
	}
	
	@RequestMapping("/datadictionarylistModify")
	private void AppinfoDataiction(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<DataDictionary> listdata = new ArrayList<>();
		listdata = dataDiction.DataDictionValueName();
		response.getWriter().write(JSONArray.toJSONString(listdata));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping("/Appinfocategorylevellist")
	private void AppinfoCategorylevellist(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("pid");
		List<AppCategory> listCategory = new ArrayList<AppCategory>();
		listCategory = appCateForty.ListCategoryLevel1(Integer.parseInt(id));
		response.getWriter().write(JSONArray.toJSONString(listCategory));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@RequestMapping("appinfoModify")
	private String AppinfoModify(AppInfo appinfo,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		boolean flog = false;
		flog = appinfoServlet.AppinfoModify(appinfo);
		if(flog) {
			return "redirect:/appinfolist";	
		}else {
			return "redirect:/appinfomodify?id="+appinfo.getId();
		}
	}
	
	@RequestMapping("/appversionmodify")
	private String AppinfoversionModify(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<AppVersion> listsion = new ArrayList<>();
		AppVersion ViteSion = new AppVersion();
		String id = request.getParameter("aid");
		String verid = request.getParameter("vid");
		listsion = appversion.AppversList(Integer.parseInt(id));
		ViteSion = appversion.AppVersionShow(Integer.parseInt(verid));
		request.setAttribute("appVersionList", listsion);
		request.setAttribute("appVersion", ViteSion);
		return "developer/appversionmodify";
	}
	
	@RequestMapping("/appversionmodifysave")
	private String AppinfoVerSionModifySava(AppVersion version,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		boolean flog = false;
		flog = appversion.AppverSionUpdate(version);
		if(flog) {
			return "redirect:/appinfolist";
		}else {
			return "redirect:/appversionmodify?vid="+version.getAppId()+"&aid"+version.getAppId();
		}
		
	}
}
