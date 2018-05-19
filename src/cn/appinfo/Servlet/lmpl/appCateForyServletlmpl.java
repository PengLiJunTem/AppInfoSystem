package cn.appinfo.Servlet.lmpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appinfo.Servlet.appCateForyServlet;
import cn.appinfo.dao.AppCateDao.AppCate;
import cn.appinfo.entity.AppCategory;

@Service("appCateForyServletlmpl")
public class appCateForyServletlmpl implements appCateForyServlet{

	@Resource
	AppCate appcatedao;
	
	@Override
	public List<AppCategory> ListCategoryLevel1(Integer id) {
		List<AppCategory> list = new ArrayList<AppCategory>();
		list = appcatedao.ListCategoryLevel1(id);
		return list;
	}
}
