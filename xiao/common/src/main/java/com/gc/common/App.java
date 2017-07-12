package com.gc.common;

import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Hello world!
 *
 */
public class App {
	public static int i = 0;

	public int a;
	public int b;

	public static void main(String[] args) {
		App.i++;
		App a = new App();
		a.i++;
		a.i++;
		System.out.println(App.class.hashCode());
		System.out.println(String.class.hashCode());
		System.out.println(a.hashCode());

		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "a1");
		map.put(null, "a2");
		System.out.println(map.get(null));
		System.out.println(null == null);
		WebClient webClient = new WebClient();
	}

}
