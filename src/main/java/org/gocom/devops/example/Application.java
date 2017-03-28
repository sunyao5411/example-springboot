/*******************************************************************************
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Mar 27, 2017 10:27:34 PM
 *******************************************************************************/

package org.gocom.devops.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application.
 *
 * @author ZhongWen Li (mailto:lizw@primeton.com)
 */
@EnableAutoConfiguration
@ComponentScan("org.gocom.devops.example")
public class Application {
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
