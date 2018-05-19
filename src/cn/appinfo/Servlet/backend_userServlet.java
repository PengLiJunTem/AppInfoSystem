package cn.appinfo.Servlet;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.BackendUser;

public interface backend_userServlet{

	public BackendUser BackuserShow(@Param("userCode")String userCode,@Param("userPassword")String userPassword);

}
