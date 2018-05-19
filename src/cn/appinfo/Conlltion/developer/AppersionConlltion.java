package cn.appinfo.Conlltion.developer;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import cn.appinfo.Servlet.appVersionServlet;
import cn.appinfo.Servlet.appinfo_Servlet;
import cn.appinfo.entity.AppVersion;

@Controller
public class AppersionConlltion {

	@Resource
	appVersionServlet appversion;
	
	@Resource
	appinfo_Servlet appinfoservlet;
	
	
	@RequestMapping("/addversionsave")
	public String ApperSionAdd(AppVersion version,HttpServletRequest request,HttpServletResponse response,@RequestParam(value="a_downloadLink",required=false)MultipartFile attach) {
		String idPicPath = null;
		if(!attach.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			String oldFileName = attach.getOriginalFilename();//源文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//源文件后缀名
			int filesize = 10000000;
			if(attach.getSize()>filesize) {//上传文件不能超过500KB
				return "redirect:/appinfoview?FileErrer="+1+"&ifog="+2+"&id="+version.getAppId();
			}else if(prefix.equals("apk")||prefix.equals("jar")||prefix.equals("java")||prefix.equals("pneg")){
				String fileName = System.currentTimeMillis()+"_Appinfo.jpg";
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/appinfoview?FileErrer="+2+"&ifog="+2+"&id="+version.getAppId();
				}
				idPicPath = path+File.separator+fileName;
				System.out.println(idPicPath);
			}else {
				return "redirect:/appinfoview?FileErrer="+3+"&ifog="+2+"&id="+version.getAppId();
			}
		}
		String url = attach.getOriginalFilename();
		String urls = "/AppInfoSystem/statics/uploadfiles/"+attach.getOriginalFilename();
		version.setDownloadLink(urls);
		version.setApkLocPath(idPicPath);
		version.setApkFileName(url);
		if(appversion.AppversAdd(version)) {
			int id = appversion.AppSelectId(version.getAppId());
			appinfoservlet.AppinVersionUpdate(id,version.getAppId());
			return "redirect:/appinfolist";
		}else {
			return "redirect:/appinfoview?ifog="+2+"&id="+version.getAppId();
		}	
	}
}
