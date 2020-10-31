package com.example.PentoApi;

import com.example.PentoApi.controller.LoginController;
import com.example.PentoApi.doa.Group;
import com.example.PentoApi.doa.User;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
public class PentoApiApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(PentoApiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		User usr = new User("xxMasterCJ21xx", "JJmm122441", "none", new ArrayList<Integer>());
		LoginController login_controller = new LoginController();
		String r = login_controller.postUser(usr);
		System.out.println(r);
		User u = login_controller.getUser("xxMasterCJ21xx");
		System.out.println(u.getUsername() + ", " + u.getPassword() + ", " + u.getRegion());
		List<User> test = login_controller.getUsers();
		for (int i = 0; i < test.size(); i++) { System.out.println(test.get(i).getUsername()); }
		String n = login_controller.deleteUser("xxMasterCJ21zz");
		System.out.println(n);
		User usr2 = new User("xxMasterCJ21xx", "newpassword", "LA", new ArrayList<Integer>());
		String p = login_controller.updateUser(usr2);
		System.out.println(p);
		//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X
	    //POST --data "{\"username\": \"johy\", \"password\": \"adadasda\"}" localhost:8080/createUser
	}
}
