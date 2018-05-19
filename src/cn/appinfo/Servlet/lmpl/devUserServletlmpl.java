package cn.appinfo.Servlet.lmpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appinfo.Servlet.devUserServlet;
import cn.appinfo.dao.dev_user.DevUserDao;
import cn.appinfo.entity.DevUser;

@Service("devUserServletlmpl")
public class devUserServletlmpl implements devUserServlet{

	@Resource
	DevUserDao devUserDao;
	
	@Override
	public DevUser DevUserShow(String name, String pass) {
		DevUser devUser = new DevUser();
		devUser = devUserDao.DevUserShow(name, pass);
		return devUser;
	}

}
