/*******************************************************************************
 *
 * Copyright (c) 2001-2017 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Jan 23, 2017 2:21:52 PM
 *******************************************************************************/

package org.gocom.devops.example;

import java.text.MessageFormat;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ApiException. <br>
 *
 * @author ZhongWen Li (mailto: lizw@primeton.com)
 */
public class ApiException extends Exception {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 4937876551787075128L;

	public ApiException() {
		super();
		// Auto-generated constructor stub
	}

	public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object ... arguments) {
		super(format(message, arguments), cause, enableSuppression, writableStackTrace);
		// Auto-generated constructor stub
	}

	public ApiException(String message, Throwable cause, Object ... arguments) {
		super(format(message, arguments), cause);
		// Auto-generated constructor stub
	}

	public ApiException(String message, Object ... arguments) {
		super(format(message, arguments));
		// Auto-generated constructor stub
	}

	public ApiException(Throwable cause) {
		super(cause);
		// Auto-generated constructor stub
	}

	protected static String format(String message, Object ... arguments) {
		if (StringUtils.isNotEmpty(message) && ArrayUtils.isNotEmpty(arguments)) {
			return MessageFormat.format(message, arguments);
		}
		return message;
	}
	
}
