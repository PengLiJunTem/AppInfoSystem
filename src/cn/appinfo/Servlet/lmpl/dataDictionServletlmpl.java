package cn.appinfo.Servlet.lmpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appinfo.Servlet.dataDictionServlet;
import cn.appinfo.dao.Datadection.DatadectionDao;
import cn.appinfo.entity.DataDictionary;

@Service("dataDictionServletlmpl")
public class dataDictionServletlmpl implements dataDictionServlet{

	@Resource
	DatadectionDao dataTionDao;
	
	@Override
	public List<DataDictionary> DataDictionList() {
		List<DataDictionary> list = new ArrayList<>();
		list = dataTionDao.DataDictionList();
		return list;
	}

	@Override
	public List<DataDictionary> DataDictionValueName() {
		List<DataDictionary> list = new ArrayList<>();
		list = dataTionDao.DataDictionValueName();
		return list;
	}

}
