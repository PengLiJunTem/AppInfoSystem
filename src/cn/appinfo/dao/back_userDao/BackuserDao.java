package cn.appinfo.dao.back_userDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.AppInfo;
import cn.appinfo.entity.BackendUser;

public interface BackuserDao {

	public BackendUser BackuserShow(@Param("userCode")String userCode,@Param("userPassword")String userPassword);
	
	public List<AppInfo> App_infoShow();
}
