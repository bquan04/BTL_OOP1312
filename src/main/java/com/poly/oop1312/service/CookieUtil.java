package com.poly.oop1312.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	//Tao va lưu cookie trên client
	public static void add(String name, String pass, int hours, HttpServletResponse resp) {
		Cookie usernameCookie = new Cookie("username", name);
		usernameCookie.setMaxAge(hours * 60 * 60);
		resp.addCookie(usernameCookie);

		Cookie passwordCookie = new Cookie("password", pass);
		passwordCookie.setMaxAge(hours * 60 * 60);
		resp.addCookie(passwordCookie);

	}

	//Lay gia tri cookie gui ve client
	public static Cookie get(String name, String value, HttpServletRequest req) {
		Cookie[] cookie = req.getCookies();
		if (cookie != null) {
			for (Cookie c : cookie) {
				if (c.getName().equalsIgnoreCase(name) && c.getValue().equalsIgnoreCase(value)) {
					return c;
				}
			}
		}
		return null;
	}

}
