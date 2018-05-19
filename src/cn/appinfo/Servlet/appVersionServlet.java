package cn.appinfo.Servlet;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.AppVersion;

public interface appVersionServlet {

	public AppVersion AppVersionShow(@Param("id")Integer id);
	
	public List<AppVersion> AppversList(@Param("appId")Integer appId);
	
	public boolean AppversAdd(AppVersion version);
	
	public int AppSelectId(@Param("appid")Integer appid);
	
	public int AppverSionDelect(@Param("appid")Integer appid);
	
	public boolean AppverSionUpdate(AppVersion version);
}
