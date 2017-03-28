/*******************************************************************************
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Mar 27, 2017 10:28:24 PM
 *******************************************************************************/

package org.gocom.devops.example;

import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * PingApi.
 *
 * @author ZhongWen Li (mailto:lizw@primeton.com)
 */
@RestController
@RequestMapping(URL.PREFIX)
public class PingApi {
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping() {
		return "pong"; //$NON-NLS-1$
	}
	
	@RequestMapping(value = "/system", method = RequestMethod.GET)
	public Properties system() {
		return System.getProperties();
	}

}
