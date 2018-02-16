package com.cash;

import com.cash.config.WebMvcConfiguration;
import com.cash.model.DashAlert;
import com.cash.model.DashMessage;
import com.cash.model.Register;
import com.cash.model.User;
import com.cash.service.RegisterService;
import com.cash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Calendar;
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

//	@Autowired
//	private RegisterService service;

	@Autowired
	private UserService userService;

	@Override
	public void run(String... strings) throws Exception {
		User user = new User().builder()
				.id(UUID.randomUUID().toString())
				.name("Fabiano Góes")
				.email("fabianogoes@gmail.com")
				.password("123")
				.build();
		userService.save(user);
		user = new User().builder()
				.id(UUID.randomUUID().toString())
				.name("Administrator")
				.email("administrator@gmail.com")
				.password("123")
				.build();
		userService.save(user);

//		service.deleteAll();
//		Register register = Register.builder()
//				.status("Paid")
//				.dueDate(Calendar.getInstance().getTime())
//				.title("Conta de Agua")
//				.amount(50.50)
//				.type("Debit")
//				.category("Water")
//				.build();
//		service.save(register);
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
