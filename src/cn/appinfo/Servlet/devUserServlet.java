package cn.appinfo.Servlet;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.DevUser;

public interface devUserServlet {

	public DevUser DevUserShow(@Param("name")String name,@Param("pass")String pass);
}
