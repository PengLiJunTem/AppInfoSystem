package cn.appinfo.dao.dev_user;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.DevUser;

public interface DevUserDao {

	public DevUser DevUserShow(@Param("name")String name,@Param("pass")String pass);
}
