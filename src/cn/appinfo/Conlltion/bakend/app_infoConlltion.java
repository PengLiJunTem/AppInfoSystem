package cn.appinfo.Conlltion.bakend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sun.org.apache.xpath.internal.operations.And;

import cn.appinfo.Print.AppinfoPrint;
import cn.appinfo.Servlet.appCateForyServlet;
import cn.appinfo.Servlet.appinfo_Servlet;
import cn.appinfo.Servlet.dataDictionServlet;
import cn.appinfo.entity.AppCategory;
import cn.appinfo.entity.AppInfo;
import cn.appinfo.entity.DataDictionary;

@Controller
public class app_infoConlltion {

	@Resource
	appinfo_Servlet appinfoServlet;

	@Resource
	appCateForyServlet appcatefory;

	@Resource
	dataDictionServlet dataDictionl;

	@RequestMapping("/manager/backend/app/list")
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
		if(request.getParameter("queryFlatformId") !=null) {
			String count = request.getParameter("queryFlatformId");
			if(Integer.parseInt(count) > 0) {
				Vite.setStatus(Integer.parseInt(count));
			}
		}
		if(request.getParameter("queryFlatformIds") !=null) {
			String count = request.getParameter("queryFlatformIds");
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
		listCate1 = appcatefory.ListCategoryLevel1(0);// 1   2   3级分类
		List<DataDictionary> listData = new ArrayList<>();//APP状态
		listData = dataDictionl.DataDictionList();
		List<DataDictionary> listDataValue = new ArrayList<>();//所属平台
		listDataValue = dataDictionl.DataDictionValueName();
		request.setAttribute("flatFormList", listDataValue);
		request.setAttribute("categoryLevel1List", listCate1);
		request.setAttribute("listData", listData);
		request.setAttribute("appInfoList", listInfo);
		request.setAttribute("P", print);
		return "backend/applist";
	}

	@RequestMapping(value="/categorylevellist",method=RequestMethod.GET)
	@ResponseBody
	private void CategoryLeveList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id = "0";
		if(request.getParameter("pid")!=null) {
			id = request.getParameter("pid");
		}
		List<AppCategory> listCate1 = new ArrayList<>();
		listCate1 = appcatefory.ListCategoryLevel1(Integer.parseInt(id));// 1   2   3级分类
		PrintWriter out = response.getWriter();
		out.write(JSONArray.toJSONString(listCate1));
		out.flush();
		out.close();
	}
}
