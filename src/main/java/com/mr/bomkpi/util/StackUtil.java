package com.mr.bomkpi.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackUtil {
	
	public static String getInvokerClass(){
		StackTraceElement[] stack = (new Throwable()).getStackTrace(); 
		String baseLineClass = null;
		for (int i = 1; i < stack.length; i++) {     
	        StackTraceElement ste = stack[i];     
	        if(baseLineClass == null)
	        	baseLineClass = ste.getClassName();
	        else
	        	if(!baseLineClass.equals(ste.getClassName())){
	        		return ste.getFileName()+"("+ste.getLineNumber()+")";
	        	}
	    }      
		return null;
	}
	
	public static String fullException(Throwable t){
		StringBuffer sb = new StringBuffer();
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	public static void main(String[] args){
//		System.out.println(StackUtil.getInvokerClass());
		System.out.println(fullException(new Throwable()));
	}

	public static String getCaller() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		StringBuffer sb = new StringBuffer();
		String clazz;
		String previousClass = null;

		String callerPackageInclude = "com.bm,cn.fcgyl";
		for (int i = 5; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			clazz = ste.getClassName();

			boolean shouldInclude = true;
			if (callerPackageInclude != null) {
				String [] callerPackageIncludes = callerPackageInclude.split(",");
				shouldInclude = false;
				for (int j = 0; j < callerPackageIncludes.length; j++) {
					if (clazz.startsWith(callerPackageIncludes[j].trim())) {
						shouldInclude = true;
						break;
					}
				}
			}

			if (!shouldInclude) {
				continue;
			}

			if (previousClass == null) {
				previousClass = clazz;
			}

			if (!previousClass.equals(clazz)) {
				sb.append("@").append(previousClass.substring(previousClass.lastIndexOf(".") + 1)).append(" ");
				previousClass = clazz;
			}

			if (sb.length() > 0) {
				sb.append("<");
			}
			;
			sb.append(ste.getMethodName());

		}

		if (previousClass != null) {
			sb.append("@").append(previousClass.substring(previousClass.lastIndexOf(".") + 1));
		}

		return sb.toString();
	}
}
