package org.excelUtil;

/** 
* @ClassName: ExcelExportTypeEnum 
* @Description: excel导出特殊类型
* @author chen.gao@baidao.com   
* @date 2016年8月19日 下午5:14:09 
*  
*/
public enum ExcelExportTypeEnum {
	/**
	 * 日期（yyyy-MM-dd）
	 */
	DATE,
	/**
	 * 时间（yyyy-MM-dd HH:mm:ss）
	 */
	TIME,
	/**
	 * 需要格式化的类型
	 */
	FORMAT,
	/**
	 * 浮点类型
	 */
	DOUBLE,
	/**
	 * 普通类型
	 */
	COMMON
}
