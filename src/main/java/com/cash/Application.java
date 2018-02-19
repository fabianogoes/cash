package com.cash;

import com.cash.config.WebMvcConfiguration;
import com.cash.model.DashAlert;
import com.cash.model.DashMessage;
import com.cash.model.User;
import com.cash.service.CategoryService;
import com.cash.service.UserService;
import com.cash.util.RegisterPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {


	public static void main(String[] args) {
		new SpringApplicationBuilder(
				Application.class,
				WebMvcConfiguration.class
		).run(args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private RegisterPropertiesUtil registerPropertiesUtil;

	@Autowired
	private CategoryService categoryService;

	@Override
	public void run(String... strings) throws Exception {
		User userAdministrator = new User().builder()
				.name("Administrator")
				.email("admin@gmail.com")
				.password("123456")
				.build();
		User userDB = userService.findByEmail(userAdministrator.getEmail());
		if(userDB == null) {
        	userService.save(userAdministrator);
        }

		registerPropertiesUtil.getCategoryRegister().forEach(categName -> {
			if(categoryService.findByName(categName) == null) {
				categoryService.saveByName(categName);
			}
		});

	}

	@Bean
	public List<DashMessage> messages(){
		return Arrays.asList(
				new DashMessage("Message 1", UUID.randomUUID().toString()),
				new DashMessage("Message 2", UUID.randomUUID().toString()),
				new DashMessage("Message 5", UUID.randomUUID().toString())
		);
	}
	@Bean
	public List<DashAlert> alerts(){
		return Arrays.asList(
				new DashAlert("Alert 1", UUID.randomUUID().toString()),
				new DashAlert("Alert 2", UUID.randomUUID().toString()),
				new DashAlert("Alert 3", UUID.randomUUID().toString())
		);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
