package cn.appinfo.Conlltion.developer;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.appinfo.Servlet.appCateForyServlet;
import cn.appinfo.Servlet.appinfo_Servlet;
import cn.appinfo.Servlet.dataDictionServlet;
import cn.appinfo.entity.AppCategory;
import cn.appinfo.entity.AppInfo;
import cn.appinfo.entity.DataDictionary;

@Controller
public class AppinfoAddConlltion {

	
	@Resource
	appinfo_Servlet appinfoServlet;
	
	@Resource
	dataDictionServlet dataDiction;
	
	@Resource
	appCateForyServlet appCateForty;
	
	@RequestMapping("/appinfoadd")
	private String AppinfoFile(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
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
		return "developer/appinfoadd";
	}
	
	@RequestMapping(value="/appinfoaddsave",method=RequestMethod.POST)
	private String addAppinfoAddSave(AppInfo appifo,AppInfo infouser,HttpSession session,HttpServletRequest request,@RequestParam(value="a_logoPicPath",required=false)MultipartFile attach) {
		String idPicPath = null;
		if(!attach.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			String oldFileName = attach.getOriginalFilename();//源文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//源文件后缀名
			int filesize = 1000000;
			if(attach.getSize()>filesize) {//上传文件不能超过500KB
				return "redirect:/appinfoadd?FileErrer="+1;
			}else if(prefix.equals("jpg")||prefix.equals("png")||prefix.equals("jpeg")||prefix.equals("pneg")){
				String fileName = System.currentTimeMillis()+"_Appinfo.jpg";
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/appinfoadd?FileErrer="+2;
				}
				idPicPath = path+File.separator+fileName;
				System.out.println(idPicPath);
			}else {
				return "redirect:/appinfoadd?FileErrer="+3+"&ifog="+2+"&id="+appifo.getAppInfo();
			}
		}
		String url = "/AppInfoSystem/statics/uploadfiles/"+attach.getOriginalFilename();
		appifo.setLogoLocPath(idPicPath);
		appifo.setLogoPicPath(url);
		boolean flog = false;
		flog = appinfoServlet.AppinfoAdd(appifo);
		if(flog) {
			return"redirect:/appinfolist";
		}else {
			return "redirect:/appinfoadd";
		}
	}
	
}
