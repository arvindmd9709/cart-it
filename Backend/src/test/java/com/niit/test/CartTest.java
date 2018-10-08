package com.niit.test;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.CartDAO;
import com.niit.model.CartItem;

public class CartTest {
	
	static CartDAO cartDAO;
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void initialize()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.*");
		context.refresh();
		cartDAO=(CartDAO)context.getBean("cartDAO");
	}
	
	
	@Test
	public void addCartItemTest()
	{
		CartItem cartItem=new CartItem();
		cartItem.setProductId(13);
		cartItem.setProductName("Samsungj7");
		cartItem.setQuantity(10);
		cartItem.setPrice(12000);
		cartItem.setUsername("vinod");
		cartItem.setPstatus("NP");
		
		assertTrue("Problem in Adding the Cart Item:",cartDAO.addCartItem(cartItem));
	}

}
