package cn.appinfo.Servlet;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.AppInfo;

public interface appinfo_Servlet {

	public List<AppInfo> App_infoShow(AppInfo Vite);
	
	public int App_infoCount();
	
	public AppInfo AppinfoMax(@Param("id")Integer id);
	
	public boolean AppinfoUpdate(@Param("id")Integer id,@Param("statusid")Integer statusid);
	
	public boolean AppinfoAPK(@Param("APKName")String APKName);
	
	public boolean AppinfoAdd(AppInfo appinfo);
	
	public AppInfo AppinfoViet(@Param("id")Integer id);
	
	public int AppinVersionUpdate(@Param("appid")Integer appid,@Param("id")Integer id);

	public int AppinfoDelect(@Param("id")Integer id);
	
	public boolean AppinfoModify(AppInfo appInfo);
	
	public boolean AppinfoModifySion(@Param("staticid")Integer staticid,@Param("id")Integer id);
}
