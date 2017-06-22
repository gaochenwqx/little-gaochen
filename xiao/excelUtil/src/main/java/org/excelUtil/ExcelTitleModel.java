package org.excelUtil;

import java.lang.reflect.Method;

/** 
* @ClassName: ExcelTitleBean 
* @Description: 用于存放Excel标题列信息的model
* @author chen.gao@baidao.com   
* @date 2016年8月22日 上午9:38:53 
*/
public class ExcelTitleModel implements Comparable<ExcelTitleModel> {

	/**
	 * 标题名称
	 */
	private String name;

	/**
	 * 标题排序
	 */
	private int sort;

	/**
	 * 字段类型
	 */
	private ExcelExportTypeEnum type;

	/**
	 * 当type类型为FORMATE/DOUBLE时，用于格式化的模板
	 */
	private String format;

	/**
	 * get方法
	 */
	private Method method;

	/**
	 * 排序
	 * @param o
	 * @return
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ExcelTitleModel o) {
		return this.sort - o.sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public ExcelExportTypeEnum getType() {
		return type;
	}

	public void setType(ExcelExportTypeEnum type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

}
