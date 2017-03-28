/*******************************************************************************
 *
 * Copyright (c) 2001-2017 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Jan 23, 2017 2:12:37 PM
 *******************************************************************************/

package org.gocom.devops.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeApi. <br>
 *
 * @author ZhongWen Li (mailto: lizw@primeton.com)
 */
@RestController
@RequestMapping(URL.PREFIX + "/employees")
public class EmployeeApi {
	
	private static String SQL_INSERT = "INSERT INTO `employee` (`no`, `name`, `mail`, `password`, `phone`, `gender`) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static String SQL_UPDATE = "UPDATE `employee` SET `no` = ?, `name` = ?, `password` = ?, `phone` = ?, `gender` = ? WHERE `mail` = ?";

	private static String SQL_DELETE = "DELETE FROM `employee` WHERE `mail` = ?";
	
	private static String SQL_SELECT = "SELECT `id`, `no`, `name`, `mail`, `password`, `phone`, `gender` FROM `employee` WHERE `mail` = ?";
	
	private static String SQL_QUERY = "SELECT `id`, `no`, `name`, `mail`, `password`, `phone`, `gender` FROM `employee`";
	
	@RequestMapping(method = RequestMethod.POST)
	public Employee add(@RequestBody Employee employee) throws ApiException {
		if (null == employee || StringUtils.isBlank(employee.getMail())) {
			return null;
		}
		if (null != get(employee.getMail())) {
			throw new ApiException("Email {0} already in use.", employee.getMail());
		}
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DbUtils.getConnection();
			stmt = connection.prepareStatement(SQL_INSERT);
			stmt.setString(1, employee.getNo());
			stmt.setString(2, employee.getName());
			stmt.setString(3, employee.getMail());
			stmt.setString(4, employee.getPassword());
			stmt.setString(5, employee.getPhone());
			stmt.setString(6, employee.getGender());
			return stmt.execute() ? employee : null;
		} catch (SQLException e) {
			throw new ApiException(e);
		} finally {
			DbUtils.closeQuietly(stmt, connection);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Employee update(@RequestBody Employee employee) throws ApiException {
		if (null == employee || StringUtils.isBlank(employee.getMail())) {
			return null;
		}
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DbUtils.getConnection();
			stmt = connection.prepareStatement(SQL_UPDATE);
			stmt.setString(1, employee.getNo());
			stmt.setString(2, employee.getName());
			stmt.setString(3, employee.getPassword());
			stmt.setString(4, employee.getPhone());
			stmt.setString(5, employee.getGender());
			stmt.setString(6, employee.getMail());
			return stmt.execute() ? employee : null;
		} catch (SQLException e) {
			throw new ApiException(e);
		} finally {
			DbUtils.closeQuietly(stmt, connection);
		}
	}
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public Employee get(@RequestParam("mail") String mail) throws ApiException {
		if (StringUtils.isBlank(mail)) {
			return null;
		}
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtils.getConnection();
			stmt = connection.prepareStatement(SQL_SELECT);
			stmt.setString(1, mail);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getString(1));
				employee.setNo(rs.getString(2));
				employee.setName(rs.getString(3));
				employee.setMail(rs.getString(4));
				employee.setPassword(rs.getString(5));
				employee.setPhone(rs.getString(6));
				employee.setGender(rs.getString(7));
				return employee;
			}
		} catch (SQLException e) {
			throw new ApiException(e);
		} finally {
			DbUtils.closeQuietly(rs, stmt, connection);
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> query() throws ApiException {
		List<Employee> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtils.getConnection();
			stmt = connection.prepareStatement(SQL_QUERY);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getString(1));
				employee.setNo(rs.getString(2));
				employee.setName(rs.getString(3));
				employee.setMail(rs.getString(4));
				employee.setPassword(rs.getString(5));
				employee.setPhone(rs.getString(6));
				employee.setGender(rs.getString(7));
				
				results.add(employee);
			}
		} catch (SQLException e) {
			throw new ApiException(e);
		} finally {
			DbUtils.closeQuietly(rs, stmt, connection);
		}
		return results;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Employee remove(@RequestParam("mail") String mail) throws ApiException {
		if (StringUtils.isBlank(mail)) {
			return null;
		}
		Employee employee = get(mail);
		if (null == employee) {
			return null;
		}
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DbUtils.getConnection();
			stmt = connection.prepareStatement(SQL_DELETE);
			stmt.setString(1, employee.getMail());
			return stmt.execute() ? employee : null;
		} catch (SQLException e) {
			throw new ApiException(e);
		} finally {
			DbUtils.closeQuietly(stmt, connection);
		}
	}

	@RequestMapping(value = "/signin", method = RequestMethod.PUT)
	public boolean signin(@RequestBody Employee employee, HttpServletRequest request) throws ApiException {
		if (null != employee && null == request.getSession().getAttribute(Employee.class.getSimpleName())) {
			Employee e = get(employee.getMail());
			if (null == e) {
				return false;
			}
			if (StringUtils.equals(employee.getMail(), e.getMail())
					&& StringUtils.equals(employee.getPassword(), e.getPassword())) {
				request.getSession().setAttribute(Employee.class.getSimpleName(), employee);
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.PUT)
	public boolean signout(HttpServletRequest request) throws ApiException {
		request.getSession().removeAttribute(Employee.class.getSimpleName());
		return true;
	}
	
}
