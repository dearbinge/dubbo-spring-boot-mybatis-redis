package com.dearbinge.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dearbinge.data.api.SecurityService;
import com.dearbinge.data.domain.ErrorResponse;
import com.dearbinge.data.pojo.Security;
import com.dearbinge.utils.JsonUtil;
import com.dearbinge.utils.ReturnCode;

public class UserSecurityInterceptor implements HandlerInterceptor {
	private static ClassPathXmlApplicationContext context = null;

	private ClassPathXmlApplicationContext getContext() {
		if (context == null) {
			synchronized (UserSecurityInterceptor.class) {
				if (context == null) {
					context = new ClassPathXmlApplicationContext(new String[] { "dubbo-services.xml" });
					context.refresh();
					context.start();
				}
			}
		}
		return context;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String secret_key = arg0.getParameter("secret_key");
		ErrorResponse errorResponse = new ErrorResponse();
		getContext();
		SecurityService securityService = (SecurityService) context.getBean("securityService");
		Security security = securityService.getSecurityByKey(secret_key); // 执行远程方法
		if (security == null) {
			errorResponse.setCode(String.valueOf(ReturnCode.SECURITY_IS_NOT_EXIST_OR_EXPIRED.ordinal()));
			errorResponse.setMsg(ReturnCode.SECURITY_IS_NOT_EXIST_OR_EXPIRED.toString());
			errorResponse.setSub_code(Integer.toHexString(ReturnCode.SECURITY_IS_NOT_EXIST_OR_EXPIRED.getCode()));
			errorResponse.setSub_msg(ReturnCode.SECURITY_IS_NOT_EXIST_OR_EXPIRED.name());
			arg1.getWriter().write(JsonUtil.convertObject2String(errorResponse));
			return false;
		}
		return true;
	}
}
