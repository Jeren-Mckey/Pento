package com.example.PentoApi;

import com.example.PentoApi.controller.LoginController;
import com.example.PentoApi.doa.User;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

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
		/**
		User usr = new User("xxMasterCJ21zz", "JJmm12121");
		LoginController login_controller = new LoginController();
		String r = login_controller.postUser(usr);
		System.out.println(r);
		User u = login_controller.getUser("xxMasterCJ21zz");
		System.out.println(u.getUsername() + ", " + u.getPassword());**/
		//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X
	    //POST --data "{\"username\": \"johy\", \"password\": \"adadasda\"}" localhost:8080/createUser
	}
}
