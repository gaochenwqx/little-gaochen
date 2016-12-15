package org.excelUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @ClassName: ExcelTitle 
* @Description: Excel表头注解
* @author chen.gao@baidao.com   
* @date 2016年8月19日 下午4:36:08 
*  
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelTitle {
	/**
	 * @Title: name 
	 * @Description: excel表头名称
	 * @return
	 */
	public String name();

	/**
	 * @Title: sort 
	 * @Description: excel字段排序 
	 * @return
	 */
	public int sort() default 0;

	/**
	 * @Title: type 
	 * @see com.ytx.crm.commons.util.excel.ExcelExportTypeEnum
	 * @Description:数据类型 
	 * @return
	 */
	public ExcelExportTypeEnum type() default ExcelExportTypeEnum.COMMON;

	/**
	 * @Title: format 
	 * @Description: 当type类型为FORMATE/DOUBLE时有效，用于格式化的模板<br />FORMATE：填写用于StringFormat的模板,<br />DOUBLE：填写用于DecimalFormat的模板
	 * @return
	 */
	public String format() default "";
}
