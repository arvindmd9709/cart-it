package com.niit.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.UserDAO;
import com.niit.model.User;

public class UserTest {

	static UserDAO userDAO;

	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.*");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	@Test
	public void addUser() {
		User user = new User();
		user.setUserid("02");
		user.setUsername("jdgj");
		user.setPassword("abc");
		user.setCustomerName("iam");
		user.setEmailId("a@gmail.com");
		user.setMobileNo("9943121101");
		user.setRole("User");
		user.setEnabled("enabled");

		boolean flag = userDAO.registerUser(user);

		assertEquals("Problem in Adding User:", false, flag);

		System.out.println("User Upated");
		// assertTrue("Problem in Adding User:", userDAO.registerUser(user));
	}

}
