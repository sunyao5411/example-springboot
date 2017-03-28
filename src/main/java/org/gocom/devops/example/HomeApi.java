/*******************************************************************************
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Mar 28, 2017 11:10:26 AM
 *******************************************************************************/

package org.gocom.devops.example;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeApi.
 *
 * @author ZhongWen Li (mailto:lizw@primeton.com)
 */
@Controller
public class HomeApi {
	
	
	@RequestMapping("/")
	public void home(HttpServletResponse response) throws IOException {
		response.sendRedirect("/index.html");
	}

}
