package cn.appinfo.Conlltion.developer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.appinfo.Print.AppinfoPrint;
import cn.appinfo.Servlet.appCateForyServlet;
import cn.appinfo.Servlet.appVersionServlet;
import cn.appinfo.Servlet.appinfo_Servlet;
import cn.appinfo.Servlet.dataDictionServlet;
import cn.appinfo.Servlet.devUserServlet;
import cn.appinfo.entity.AppCategory;
import cn.appinfo.entity.AppInfo;
import cn.appinfo.entity.DataDictionary;
import cn.appinfo.entity.DevUser;



@Controller
class backend_userConlltionDIV {

	@Resource
	devUserServlet devselet;
	
	@Resource
	appinfo_Servlet appinfoServlet;
	
	@Resource
	appCateForyServlet appcateforyServlet;
	
	@Resource
	dataDictionServlet datadictiopn;
	
	@Resource
	appVersionServlet appvasion;
	
	@RequestMapping("/logins")
	private String BackUserShow(HttpServletRequest request){
		String serr = request.getParameter("errerid");
		if(serr!=null) {
			request.setAttribute("error", "密码或用户名错误");
		}
		
		return "devlogin";
	}
	
	@RequestMapping("/DenLulogin")
	private String BackUserDenLu(HttpServletRequest request) {
		String name = request.getParameter("devCode");
		String pass = request.getParameter("devPassword");
		DevUser Vite = new DevUser();
		Vite = devselet.DevUserShow(name, pass);
		if(Vite != null) {
			request.setAttribute("devUser", Vite);
			return "developer/main";
		}else {
			return "redirect:/logins?errerid="+1;
		}
	}
	
	@RequestMapping("/dev/logout")
	private String BackUserOut() {
		return "devlogin";
	}
	
	@RequestMapping("/appinfolist")
	private String App_InfoShow(HttpServletRequest request) {
		AppinfoPrint print = new AppinfoPrint();
		AppInfo Vite = new AppInfo();
		print.setPretionSum(appinfoServlet.App_infoCount());
		if(request.getParameter("pageIndex")!=null) {
			String count = request.getParameter("pageIndex");
			if(Integer.parseInt(count) > print.getPretionCoulltion()) {
				count = "1";
			}
			print.setPretionCount(Integer.parseInt(count));
		}
		if(request.getParameter("queryCategoryLevel1") !=null) {
			String count = request.getParameter("queryCategoryLevel1");
			if(Integer.parseInt(count) > 0) {
				Vite.setStatus(Integer.parseInt(count));
			}
		}
		if(request.getParameter("queryCategoryLevel2") !=null) {
			String count = request.getParameter("queryCategoryLevel2");
			if(Integer.parseInt(count) > 0) {
				Vite.setFlatformId(Integer.parseInt(count));
			}
		}
		if(request.getParameter("queryCategoryLevel1") !=null && request.getParameter("queryCategoryLevel1") != "") {
			String count1 = request.getParameter("queryCategoryLevel1");
			if(Integer.parseInt(count1) > 0) {
				Vite.setCategoryLevel1(Integer.parseInt(count1));
				if(request.getParameter("queryCategoryLevel2") !=null && request.getParameter("queryCategoryLevel2") != "") {
					String count2 = request.getParameter("queryCategoryLevel2");
					if(Integer.parseInt(count2) > 0) {
						Vite.setCategoryLevel2(Integer.parseInt(count2));
						if(request.getParameter("queryCategoryLevel3") !=null && request.getParameter("queryCategoryLevel3") != "") {
							String count3 = request.getParameter("queryCategoryLevel3");
							if(Integer.parseInt(count3) > 0) {
								Vite.setCategoryLevel3(Integer.parseInt(count3));
							}
						}
					}
				}
			}
		}
		Vite.setCorintllTion((print.getPretionCount()-1)*5);
		List<AppInfo> listInfo = new ArrayList<>();
		listInfo = appinfoServlet.App_infoShow(Vite); //全部信息
		List<AppCategory> listCate1 = new ArrayList<>();
		listCate1 = appcateforyServlet.ListCategoryLevel1(0);// 1   2   3级分类
		List<DataDictionary> listData = new ArrayList<>();//APP状态
		listData = datadictiopn.DataDictionList();
		List<DataDictionary> listDataValue = new ArrayList<>();//所属平台
		listDataValue = datadictiopn.DataDictionValueName();
		request.setAttribute("flatFormList", listDataValue);
		request.setAttribute("categoryLevel1List", listCate1);
		request.setAttribute("listData", listData);
		request.setAttribute("appInfoList", listInfo);
		request.setAttribute("P", print);
		return "developer/appinfolist";
	}
	
	@RequestMapping("/Appinfodelapp")
	private void AppinfoDelect(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		Map<String, String> redomMap = new HashMap<String,String>();
		appvasion.AppverSionDelect(Integer.parseInt(id));
		int count = appinfoServlet.AppinfoDelect(Integer.parseInt(id));
		if(count>0) {
			redomMap.put("delResult", "true");
		}else if(count==0) {
			redomMap.put("delResult", "false");
		}else {
			redomMap.put("delResult", "notexist");
		}
		PrintWriter out = response.getWriter();
		out.write(JSONArray.toJSONString(redomMap));
		out.flush();
		out.close();
	}
	
	@RequestMapping("/AppinfoUpdateStatce")
	private void AppinfoUpdateStatce(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> reMap = new HashMap<String,String>();
		String id = request.getParameter("id");
		String staticid = request.getParameter("staticid");
		boolean flog = false;
		flog = appinfoServlet.AppinfoModifySion(Integer.parseInt(staticid), Integer.parseInt(id));
		if(flog) {
			reMap.put("infoStatic", "true");
			response.getWriter().write(JSONArray.toJSONString(reMap));
		}else {
			reMap.put("infoStatic", "false");
			response.getWriter().write(JSONArray.toJSONString(reMap));
		}
	}
}
