package com.zb.api.fuelrod.dao.core;

import com.zb.api.commons.model.PageBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Repository
public class CommonDao<T> implements ICommonDao<T> {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;  

	@Override
	public int insert(String statement, Object entity) {
		return sqlSessionTemplate.insert(statement, entity);
	}

	@Override
	public int updateByMap(String statement, Map map) {
		return sqlSessionTemplate.update(statement, map);
	}

	@Override
	public int updateByEntity(String statement, Object entity) {
		return sqlSessionTemplate.update(statement, entity);
	}


	@Override
	public int deleteByMap(String statement, Map map) {
		return sqlSessionTemplate.delete(statement, map);
	}

	@Override
	public <T> T findOneByMap(String statement, Map map) {
		return sqlSessionTemplate.selectOne(statement, map);
	}
	
	@Override
	public <T> T findOneByField(String statement,Object field) {
		return sqlSessionTemplate.selectOne(statement, field);
	}
	
	@Override
	public <T> T findOneByEntity(String statement, Object entity) {
		return sqlSessionTemplate.selectOne(statement, entity);
	}
	


	@Override
	public <T> List<T> findListByMap(String statement, Map map) {
		return sqlSessionTemplate.selectList(statement, map);
	}
	

	@Override
	public <T> List<T> findListByEntity(String statement,
			Object entity) {
		return sqlSessionTemplate.selectList(statement, entity);
	}
	
	@Override
	public <T> List<T> findListByField(String statement,
			Object field) {
		return sqlSessionTemplate.selectList(statement, field);
	}


	@Override
	public <T> PageBean<T> getPage(String listName,String countName, Map paramMap, int pageNo, int pageSize) {
		PageBean<T> page=new PageBean<T>();
		page.setPage(pageNo);
		page.setCount(pageSize);
		paramMap.put("page", page);
		int count=sqlSessionTemplate.selectOne(countName,paramMap);
		List<T> list=sqlSessionTemplate.selectList(listName, paramMap);
		page.setList(list);
		page.setTotal(count);
		return page;
	}

	@Override
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}



}
