package cn.appinfo.Servlet.lmpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.appinfo.Servlet.appinfo_Servlet;
import cn.appinfo.dao.app_info.AppinfoDao;
import cn.appinfo.entity.AppInfo;

@Service("appinfo_Servletlmpl")
public class appinfo_Servletlmpl implements appinfo_Servlet{

	@Resource
	AppinfoDao appinfos;
	
	@Override
	public List<AppInfo> App_infoShow(AppInfo Vite) {
		List<AppInfo> list = new ArrayList<AppInfo>();
		list = appinfos.App_infoShow(Vite);
		return list;
	}

	@Override
	public int App_infoCount() {
		int count = appinfos.App_infoCount();
		return count;
	}

	@Override
	public AppInfo AppinfoMax(Integer id) {
		AppInfo Vite = new AppInfo();
		Vite = appinfos.AppinfoMax(id);
		return Vite;
	}

	@Override
	public boolean AppinfoUpdate(Integer id, Integer statusid) {
		boolean flog = false;
		if(appinfos.AppinfoUpdate(id, statusid)>0) {
			flog = true;
		}
		return flog;
	}

	@Override
	public boolean AppinfoAPK(String APKName) {
		boolean flog = false;
		if(appinfos.AppinfoAPK(APKName)>0) {
			flog = true;
		}
		return flog;
	}

	@Override
	public boolean AppinfoAdd(AppInfo appinfo) {
		boolean flog = false;
		if(appinfos.AppinfoAdd(appinfo)>0) {
			flog = true;
		}
		return flog;
	}

	@Override
	public AppInfo AppinfoViet(Integer id) {
		AppInfo Vite = new AppInfo();
		Vite = appinfos.AppinfoViet(id);
		return Vite;
	}

	@Override
	public int AppinVersionUpdate(Integer appid, Integer id) {
		return appinfos.AppinVersionUpdate(appid, id);
	}

	@Override
	public int AppinfoDelect(Integer id) {
		return appinfos.AppinfoDelect(id);
	}

	@Override
	public boolean AppinfoModify(AppInfo appInfo) {
		boolean flog = false;
		if(appinfos.AppinfoModify(appInfo)>0) {
			flog = true;
		}
		return flog;
	}

	@Override
	public boolean AppinfoModifySion(Integer staticid, Integer id) {
		boolean flog = false;
		if(appinfos.AppinfoModifySion(staticid, id)>0) {
			flog = true;
		}
		return flog;
	}
	
}
