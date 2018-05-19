package cn.appinfo.dao.app_version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.AppVersion;

public interface AppVersionDao {

	public AppVersion AppVersionShow(@Param("id")Integer id);
	
	public List<AppVersion> AppversList(@Param("appId")Integer appId);
	
	public int AppversAdd(AppVersion version);
	
	public int AppSelectId(@Param("appid")Integer appid);
	
	public int AppverSionDelect(@Param("appid")Integer appid);
	
	public int AppverSionUpdate(AppVersion version);
}
