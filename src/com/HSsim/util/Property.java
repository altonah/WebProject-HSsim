package com.HSsim.util;

import java.lang.reflect.Method;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

public class Property {

	public final static <P extends DTO> void setProperty(HttpServletRequest request, P dto) throws Exception {
		
		Method[] methodArr = dto.getClass().getDeclaredMethods();
		
		for(int i=0; i<methodArr.length; i++){
			String methodName = methodArr[i].getName();
			if(methodName.startsWith("set")){
				System.out.println(methodName.toLowerCase());
    			if(methodArr[i].getParameterTypes()[0] == int.class){
    				methodArr[i].invoke(dto, Integer.parseInt(request.getParameter(methodName.replace("set", "").toLowerCase())));
    			}else if(methodArr[i].getParameterTypes()[0] == String.class){
    				methodArr[i].invoke(dto, request.getParameter(methodName.replace("set", "").toLowerCase()));
    			}else if(methodArr[i].getParameterTypes()[0] == Date.class){
    				methodArr[i].invoke(dto, Date.parse(request.getParameter(methodName.replace("set", "").toLowerCase())));
    			}
			}
		}
	}
	
}
