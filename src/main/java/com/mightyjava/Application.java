package com.mightyjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mightyjava.domain.Book;
import com.mightyjava.domain.Role;
import com.mightyjava.domain.User;
import com.mightyjava.service.IService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private IService<Book> bookService;
	@Autowired
	private IService<User> userService;
	@Autowired
	private IService<Role> roleService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleService.saveOrUpdate(new Role(1L, "admin"));
		roleService.saveOrUpdate(new Role(2L, "user"));
		
		User user1 = new User();
		user1.setEmail("test@user.com");
		user1.setName("Test User");
		user1.setMobile("9787456545");
		user1.setRole(roleService.findById(2L).get());
		user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
		userService.saveOrUpdate(user1);
		
		User user2 = new User();
		user2.setEmail("test@admin.com");
		user2.setName("Test Admin");
		user2.setMobile("9787456545");
		user2.setRole(roleService.findById(1L).get());
		user2.setPassword(new BCryptPasswordEncoder().encode("testadmin"));
		userService.saveOrUpdate(user2);
		
		Book book = new Book();
		book.setTitle("Spring Microservices in Action");
		book.setAuthor("John Carnell");
		book.setCoverPhotoURL("https://images-na.ssl-images-amazon.com/images/I/417zLTa1uqL._SX397_BO1,204,203,200_.jpg");
		book.setIsbnNumber(1617293989L);
		book.setPrice(2776.00);
		book.setLanguage("English");
		bookService.saveOrUpdate(book);
	}

}
