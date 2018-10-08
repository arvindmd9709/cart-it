package com.niit.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.DAO.CartDAO;
import com.niit.DAO.CartDAOImpl;
import com.niit.DAO.CategoryDAO;
import com.niit.DAO.CategoryDAOImpl;
import com.niit.DAO.ProductDAO;
import com.niit.DAO.ProductDAOImpl;
import com.niit.DAO.UserDAO;
import com.niit.DAO.UserDAOImpl;
import com.niit.model.CartItem;
import com.niit.model.Category;
import com.niit.model.OrderDetail;
import com.niit.model.Product;
import com.niit.model.Supplier;
import com.niit.model.User;

@Configuration
@ComponentScan("com.niit.*")
@EnableTransactionManagement

public class DBConfig {

	@Bean(name = "dataSource")
	public DataSource getH2DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/S190121");
		dataSource.setUsername("dteja");
		dataSource.setPassword("dteja");
		return dataSource;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		System.out.println("db initiated");
		LocalSessionFactoryBuilder localsessionFactory = new LocalSessionFactoryBuilder(getH2DataSource());
		localsessionFactory.addProperties(hibernateProperties);

		localsessionFactory.addAnnotatedClass(Category.class);
		localsessionFactory.addAnnotatedClass(Product.class);
		localsessionFactory.addAnnotatedClass(Supplier.class);
		localsessionFactory.addAnnotatedClass(User.class);
		localsessionFactory.addAnnotatedClass(CartItem.class);
		localsessionFactory.addAnnotatedClass(OrderDetail.class);

		System.out.println("class file.........");
		SessionFactory sessionFactory = localsessionFactory.buildSessionFactory();

		return sessionFactory;
	}

	@Bean(name = "txManager")
	public HibernateTransactionManager getHibernateTransactionmanager(SessionFactory sessionFactory) {
		System.out.println("transcation manager");
		return new HibernateTransactionManager(sessionFactory);
	}

	@Autowired(required = true)
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {

		System.out.println("User Added");
		return new UserDAOImpl(sessionFactory);

	}

	@Autowired(required = true)
	@Bean(name = "categoryDAO")
	public CategoryDAO getCategoryDAO(SessionFactory sessionFactory) {

		System.out.println("Category Added");
		return new CategoryDAOImpl(sessionFactory);

	}

	@Autowired(required = true)
	@Bean(name = "cartDAO")
	public CartDAO getCartDAO(SessionFactory sessionFactory) {

		System.out.println("Cart items Added");
		return new CartDAOImpl(sessionFactory);

	}

	@Autowired(required = true)
	@Bean(name = "productDAO")
	public ProductDAO getProductDAO(SessionFactory sessionFactory) {

		System.out.println("Products Added");
		return new ProductDAOImpl(sessionFactory);

	}

}
