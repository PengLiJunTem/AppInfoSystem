package cn.appinfo.Conlltion.developer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jdt.internal.compiler.tool.EclipseCompiler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.appinfo.Servlet.appVersionServlet;
import cn.appinfo.Servlet.appinfo_Servlet;
import cn.appinfo.Servlet.dataDictionServlet;
import cn.appinfo.entity.AppInfo;
import cn.appinfo.entity.AppVersion;
import cn.appinfo.entity.DataDictionary;
import sun.launcher.resources.launcher;

@Controller
public class AppinfoConlltionsvu {

	@Resource
	appinfo_Servlet appinfoServlet;

	@Resource
	dataDictionServlet dataDction;

	@Resource
	appVersionServlet appversion;
	
	@RequestMapping("/datadictionarylist")
	private void DatadictionShow(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<DataDictionary> listdata = new ArrayList<DataDictionary>();
		listdata = dataDction.DataDictionValueName();
		PrintWriter out = response.getWriter();
		out.write(JSONArray.toJSONString(listdata));
		out.flush();
		out.close();
	}

	@RequestMapping("/apkexist")
	private void AppinfoAKP(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String, String> mapst = new HashMap<String, String>();
		if(request.getParameter("APKName")!=null && request.getParameter("APKName")!= "") {
			String AKP = request.getParameter("APKName");
			boolean folg = false;
			folg = appinfoServlet.AppinfoAPK(AKP);
			if(folg) {
				mapst.put("APKName","exist");
			}else{
				mapst.put("APKName","noexist");
			}
		}else {
			mapst.put("APKName","empty");
		}
		PrintWriter out = response.getWriter();
		out.write(JSONArray.toJSONString(mapst));
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("/appinfoview")
	private String AppinfoShowView(HttpServletRequest request) {
		String ifog = request.getParameter("ifog");
		String id = request.getParameter("id");
		List<AppVersion> listVier = new ArrayList<AppVersion>();
		listVier = appversion.AppversList(Integer.parseInt(id));
		request.setAttribute("appVersion", listVier);
		AppInfo appinfovie = new AppInfo();
		appinfovie = appinfoServlet.AppinfoViet(Integer.parseInt(id));
		request.setAttribute("appInfo", appinfovie);
		if(ifog.equals("1")) {
			return "developer/appinfoview";
		}else{
			if(request.getParameter("FileErrer")!=null) {
				String FileErrer = request.getParameter("FileErrer");
				String FileName = null;
				if(FileErrer.equals("1")) {
					FileName = "*上传大小不能超过1M";
				}
				if(FileErrer.equals("2")) {
					FileName = "*上传失败！";
				}
				if(FileErrer.equals("3")) {
					FileName = "*上传图片格式不正确";
				}
				request.setAttribute("FileErrer", FileName);
			}
			return "developer/appversionadd";
		}
	}
}
