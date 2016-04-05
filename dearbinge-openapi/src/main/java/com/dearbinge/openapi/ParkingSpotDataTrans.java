package com.dearbinge.openapi;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dearbinge.data.api.SecurityService;
import com.dearbinge.security.interceptor.UserSecurityInterceptor;
import com.dearbinge.service.InfrastructDeal;

@Controller
@ImportResource({ "classpath:applicationContext.xml", "dubbo-services.xml" })
@EnableAutoConfiguration
public class ParkingSpotDataTrans extends WebMvcConfigurerAdapter {
	@Resource
	private Map<String, InfrastructDeal> posDataServiceMap;
	@Resource
	private SecurityService securityService;

	@RequestMapping(value = "/getSession", method = RequestMethod.GET)
	public void getSession(HttpServletRequest request, HttpServletResponse response) {
		try {
			/* 得到指定的head头的值 */
			// String headValue = request.getHeader("host");
			// System.out.println(headValue);
			// System.out.println("-------------------------------------------------");
			// Enumeration<String> e = request.getHeaders("Accept-Encoding");
			// while (e.hasMoreElements()) {
			// headValue = (String) e.nextElement();
			// String value = request.getHeader(headValue);
			// System.out.println(headValue + "=" + value);
			// }
			// System.out.println("-------------------------------------------------");
			// e = request.getHeaderNames();
			// while (e.hasMoreElements()) {
			// String name = (String) e.nextElement();
			// String value = request.getHeader(name);
			// System.out.println(name + " = " + value);
			//
			// }
			HttpSession session = request.getSession();
			session.setAttribute("username", "chubin");
			response.getWriter().write(
					"this is node3, port:8082,address:" + InetAddress.getLocalHost() + ",sessionid:" + session.getId());
		} catch (IOException ex) {
		}
	}

	@RequestMapping(value = "/getUserName", method = RequestMethod.GET)
	public void getUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			response.getWriter()
					.write("this is node3, port:8082,address:" + InetAddress.getLocalHost() + ",attribute:"
							+ (session == null ? "no session found"
									: (session.getAttribute("username") == null ? "no attribute found"
											: session.getAttribute("username").toString())));
		} catch (IOException ex) {
			throw ex;
		}
	}

	@RequestMapping(value = "/syncParkingBasicData", method = RequestMethod.POST)
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		try {
			InfrastructDeal ideal = posDataServiceMap.get(method);
			String responseResult = "";
			if (ideal != null) {
				responseResult = ideal.accept(request);
			}
			response.getWriter().write(responseResult);
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/syncParkingBasicData");

	}
}
