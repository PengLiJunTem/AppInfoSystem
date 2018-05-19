package cn.appinfo.Servlet.lmpl;


import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appinfo.Servlet.backend_userServlet;
import cn.appinfo.dao.back_userDao.BackuserDao;
import cn.appinfo.entity.BackendUser;

@Service("backend_userServletlmpl")
public class backend_userServletlmpl  implements backend_userServlet{

	@Resource
	BackuserDao backuserdao;
	
	@Override
	public BackendUser BackuserShow(String userCode, String userPassword) {
		BackendUser user = new BackendUser();
		user = backuserdao.BackuserShow(userCode, userPassword);
		return user;
	}

}
