package com.niit.test;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.CategoryDAO;
import com.niit.model.Category;

public class CategoryTest {

	static CategoryDAO categoryDAO;

	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");

	}

	
	@Test
	public void addCategoryTest() {
		Category category = new Category();
		category.setCategoryName("Vivo");
		category.setCategoryId(12);
		category.setCategoryDesc("All the Vivo Category Mobile");
	
		assertTrue("Problem in Adding Category:", categoryDAO.addCategory(category));
		System.out.println("Category set");
	}

}
