package com.HSsim.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DTO {

	final private String tableName;
	final private Field[] fieldArr;
	final private Method[] methodArr;
	
	public DTO(){
		tableName = getClass().getSimpleName().replace("DTO", "");
		fieldArr = getClass().getDeclaredFields();
		methodArr = getClass().getDeclaredMethods();	
	}

	public String getTableName() {
		return tableName;
	}

	public Field[] getFieldArr() {
		return fieldArr;
	}

	public Method[] getMethodArr() {
		return methodArr;
	}
	
	
}
