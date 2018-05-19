package cn.appinfo.Servlet.lmpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appinfo.Servlet.appVersionServlet;
import cn.appinfo.dao.app_version.AppVersionDao;
import cn.appinfo.entity.AppVersion;

@Service("appVersionServletlmpl")
public class appVersionServletlmpl implements appVersionServlet{

	@Resource
	AppVersionDao appver;
	
	@Override
	public AppVersion AppVersionShow(Integer id) {
		AppVersion Avite = new AppVersion();
		Avite = appver.AppVersionShow(id);
		return Avite;
	}

	@Override
	public List<AppVersion> AppversList(Integer appId) {
		List<AppVersion> list = new ArrayList<>();
		list = appver.AppversList(appId);
		return list;
	}

	@Override
	public boolean AppversAdd(AppVersion version) {
		boolean flog = false;
		if(appver.AppversAdd(version)>0) {
			flog = true;
		}
		return flog;
	}

	@Override
	public int AppSelectId(Integer appid) {
		return appver.AppSelectId(appid);
	}

	@Override
	public int AppverSionDelect(Integer appid) {
		return appver.AppverSionDelect(appid);
	}

	@Override
	public boolean AppverSionUpdate(AppVersion version) {
		boolean flog = false;
		if(appver.AppverSionUpdate(version)>0) {
			flog = true;
		}
		return flog;
	}
}
