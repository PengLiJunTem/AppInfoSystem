package cn.appinfo.dao.app_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.AppInfo;

public interface AppinfoDao {

	public List<AppInfo> App_infoShow(AppInfo Vite);
	
	public int App_infoCount();
	
	public AppInfo AppinfoMax(@Param("id")Integer id);
	
	public int AppinfoUpdate(@Param("id")Integer id,@Param("statusid")Integer statusid);
	
	public int AppinfoAPK(@Param("APKName")String APKName);
	
	public int AppinfoAdd(AppInfo appinfo);
	
	public AppInfo AppinfoViet(@Param("id")Integer id);
	
	public int AppinVersionUpdate(@Param("appid")Integer appid,@Param("id")Integer id);
	
	public int AppinfoDelect(@Param("id")Integer id);
	
	public int AppinfoModify(AppInfo appInfo);
	
	public int AppinfoModifySion(@Param("staticid")Integer staticid,@Param("id")Integer id);
}
