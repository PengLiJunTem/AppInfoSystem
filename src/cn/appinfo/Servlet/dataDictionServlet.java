package cn.appinfo.Servlet;

import java.util.List;

import cn.appinfo.entity.DataDictionary;

public interface dataDictionServlet {

	public List<DataDictionary> DataDictionList();
	
	public List<DataDictionary> DataDictionValueName();
}
