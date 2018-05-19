package cn.appinfo.dao.AppCateDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.entity.AppCategory;

public interface AppCate {

	public List<AppCategory> ListCategoryLevel1(@Param("id")Integer id);//一级分类
}
