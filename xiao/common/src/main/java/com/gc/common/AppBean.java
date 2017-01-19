package com.gc.common;

import java.util.Date;

/** 
* @ClassName: AppBean 
* @Description: TODO
* @author chen.gao@baidao.com   
* @date 2017年1月19日 下午3:13:01 
*  
*/
public class AppBean {
	private Long id;
	private String name;
	private Integer age;
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AppBean() {
	}

	/**   
	 * 创建一个新的实例 AppBean.   
	 *   
	 * @param id
	 * @param name
	 * @param age
	 * @param date   
	 */

	public AppBean(Long id, String name, Integer age, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.date = date;
	}

}
