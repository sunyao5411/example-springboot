/*******************************************************************************
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Feb 23, 2017 9:28:19 AM
 *******************************************************************************/

package org.gocom.devops.example;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;

/**
 * DbUtils.
 *
 * @author ZhongWen Li (mailto:lizw@primeton.com)
 */
public class DbUtils {
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&autoReconnectForPools=true&failOverReadOnly=false";
	public static final String USER = "root";
	public static final String PASS = "root";
	
	public static final int WAY_DEFAULT = 0;
	public static final int WAY_OS_ENV = 1;
	public static final int WAY_JVM_ENV = 2;
	public static final int WAY_CLASSPATH_FILE = 3;
	public static final int WAY_LOCAL_FILE = 4;
	
	public static int WAY = -1;
	
	public static final Properties DB_INFO = new Properties();
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected static int getConfigWay() {
		if (WAY >= 0) {
			return WAY;
		}
		File f = new File("/tmp/jdbc.properties");
		if (f.exists() && f.isFile()) {
			WAY = WAY_LOCAL_FILE;
			InputStream input = null;
			try {
				input = new FileInputStream(f);
				DB_INFO.load(input);
				return WAY;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(input);
			}
		}
		InputStream input = DbUtils.class.getResourceAsStream("/jdbc.properties");
		if (null != input) {
			try {
				DB_INFO.load(input);
				WAY = WAY_CLASSPATH_FILE;
				return WAY;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(input);
			}
		}
		if (StringUtils.isNotBlank(System.getProperty("jdbc_url"))) {
			DB_INFO.setProperty("jdbc_url", System.getProperty("jdbc_url"));
			DB_INFO.setProperty("jdbc_user", System.getProperty("jdbc_user"));
			DB_INFO.setProperty("jdbc_password", System.getProperty("jdbc_password"));
			DB_INFO.setProperty("jdbc_driver", System.getProperty("jdbc_driver"));
			WAY = WAY_JVM_ENV;
			return WAY;
		}
		if (StringUtils.isNotBlank(System.getenv("jdbc_url"))) {
			DB_INFO.setProperty("jdbc_url", System.getenv("jdbc_url"));
			DB_INFO.setProperty("jdbc_user", System.getenv("jdbc_user"));
			DB_INFO.setProperty("jdbc_password", System.getenv("jdbc_password"));
			DB_INFO.setProperty("jdbc_driver", System.getenv("jdbc_driver"));
			WAY = WAY_OS_ENV;
			return WAY;
		}
		WAY = WAY_DEFAULT;
		return WAY;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		if (WAY_DEFAULT == getConfigWay()) {
			return DriverManager.getConnection(URL, USER, PASS);
		}
		return DriverManager.getConnection(
				DB_INFO.getProperty("jdbc_url"), 
				DB_INFO.getProperty("jdbc_user"), 
				DB_INFO.getProperty("jdbc_password"));
	}
	
	/**
	 * 
	 * @param closeables
	 */
	public static void closeQuietly(Object ... closeables) {
		if (null == closeables || 0 == closeables.length) {
			return;
		}
		for (Object closeable : closeables) {
			if (null == closeable) {
				continue;
			}
			if (closeable instanceof Closeable) {
				IOUtils.closeQuietly((Closeable)closeable);
			} else {
				try {
					MethodUtils.invokeMethod(closeable, "close", null); //$NON-NLS-1$
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
