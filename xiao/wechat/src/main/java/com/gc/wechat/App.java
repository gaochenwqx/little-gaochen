package com.gc.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gc.common.AppBean;

@Controller
@EnableAutoConfiguration
@RequestMapping("app")
public class App {

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AppBean get(@PathVariable("id") Long id) {
		return new AppBean(id, "默认" + id, 12, new Date());
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<AppBean> query(AppBean query) {
		List<AppBean> list = new ArrayList<AppBean>();
		for (int i = 0; i < 10; i++) {
			list.add(new AppBean(i + 0L, query.getName() + i, query.getAge(), query.getDate()));
		}
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AppBean add(@RequestBody AppBean query) {
		return query;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}