package com.zb.api.fuelrod.dao.core;

import com.zb.api.commons.model.PageBean;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;


public interface ICommonDao<T> {
	
	/**
	 * 获取SqlSessionTemplate
	 * @return
	 */
	public SqlSessionTemplate getSqlSessionTemplate();

	/**
	 * 插入数据
	 * @param statement	命名空间+ID
	 * @param entity	实体对象
	 * @return 
	 */
	public int insert(String statement, Object entity);

	

	/**
	 * 根据map修改
	 * @param statement	命名空间+ID
	 * @param map		map对象
	 * @return	成功true, 否则false.
	 * @ 
	 */
	public int updateByMap(String statement, Map map);

	/**
	 * 根据实体对象修改
	 * @param statement	命名空间+ID
	 * @param entity	实体对象
	 * @return	成功true, 否则false.
	 * @ 
	 */
	public int updateByEntity(String statement, Object entity);


	/**
	 * 根据map删除
	 * @param statement	命名空间+ID
	 * @param map		map对象
	 * @return	成功true, 否则false.
	 * @ 
	 */
	public int deleteByMap(String statement, Map map);



	/**
	 * 根据map查询
	 * @param statement	命名空间+ID
	 * @param map		map对象
	 * @return	T实体对象
	 */
	public <T> T findOneByMap(String statement, Map map);
	
	/**
	 * 根据map查询
	 * @param statement	命名空间+ID
	 * @param field		field
	 * @return	T实体对象
	 */
	public <T> T findOneByField(String statement, Object field);
	
	/**
	 * 根据map查询
	 * @param statement	命名空间+ID
	 * @param entity	entity
	 * @return	T实体对象
	 */
	public <T> T findOneByEntity(String statement, Object entity);



	/**
	 * 根据map查询
	 * @param statement	命名空间+ID
	 * @param map		map对象
	 * @return	T实体对象的集合
	 */
	public <T> List<T> findListByMap(String statement, Map map);
	
	/**
	 * 根据map查询
	 * @param statement	命名空间+ID
	 * @param entity	entity
	 * @return	T实体对象的集合
	 */
	public <T> List<T> findListByEntity(String statement, Object entity);
	
	/**
	 * 根据map查询
	 * @param statement	命名空间+ID
	 * @param field field
	 * @return	T实体对象的集合
	 */
	public <T> List<T> findListByField(String statement, Object field);


	/**
	 * 分页查询
	 * @param listName  命名空间+查询列表ID
	 * @param countName  命名空间+查询总数ID
	 * @param paramMap paramMap对象
	 * @param pageNo 当前页,从1开始
	 * @param pageSize 页码数
	 * @return
	 */
	public <T> PageBean<T> getPage(String listName, String countName, Map paramMap, int pageNo, int pageSize);
	
	
}
