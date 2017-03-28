/*******************************************************************************
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Feb 23, 2017 5:13:11 PM
 *******************************************************************************/

package org.gocom.devops.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConfigurationApi.
 *
 * @author ZhongWen Li (mailto:lizw@primeton.com)
 */
@RestController
@RequestMapping(URL.PREFIX + "/configurations")
public class ConfigurationApi {
	
	@RequestMapping(value = "/jdbc", method = RequestMethod.GET)
	public JdbcConfiguration getJdbcConfiguration() throws ApiException {
		if (DbUtils.WAY == DbUtils.WAY_DEFAULT) {
			return new JdbcConfiguration(DbUtils.URL, DbUtils.USER, DbUtils.PASS);
		}
		return new JdbcConfiguration(DbUtils.DB_INFO.getProperty("jdbc_url"), DbUtils.DB_INFO.getProperty("jdbc_user"),
				DbUtils.DB_INFO.getProperty("jdbc_password"));
	}
	
	@RequestMapping(value = "/jdbc", method = RequestMethod.PUT)
	public boolean setJdbcConfiguration(@RequestBody JdbcConfiguration configuration) throws ApiException {
		DbUtils.DB_INFO.setProperty("jdbc_url", configuration.getUrl());
		DbUtils.DB_INFO.setProperty("jdbc_user", configuration.getUser());
		DbUtils.DB_INFO.setProperty("jdbc_password", configuration.getPassword());
		
		OutputStream out = null;
		File f = new File ("/tmp/jdbc.properties");
		try {
			if (f.exists()) {
				f.delete();
			}
			f.createNewFile();
			out = new FileOutputStream(f);
			DbUtils.DB_INFO.store(out, "# Auto save");
			DbUtils.WAY = DbUtils.WAY_LOCAL_FILE;
		} catch (IOException e) {
			throw new ApiException(e);
		} finally {
			IOUtils.closeQuietly(out);
		}
		return true;
	}
	
	@RequestMapping(value = "/jdbc/init", method = RequestMethod.PUT)
	public boolean initialize() throws ApiException {
		DbInitUtils.initialize();
		return true;
	}
	
	@RequestMapping(value = "/systems", method = RequestMethod.GET)
	public Properties getSystemProperties() {
		return System.getProperties();
	}
	
	@RequestMapping(value = "/environments", method = RequestMethod.GET)
	public Map<String, String> getEnvironments() {
		return System.getenv();
	}

}
