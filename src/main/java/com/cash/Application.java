package com.cash;

import com.cash.model.DashAlert;
import com.cash.model.DashMessage;
import com.cash.model.Register;
import com.cash.model.User;
import com.cash.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private RegisterService service;

	@Override
	public void run(String... strings) throws Exception {
		service.deleteAll();
		Register register = Register.builder()
				.status("Paid")
				.dueDate(Calendar.getInstance().getTime())
				.title("Conta de Agua")
				.amount(50.50)
				.type("Debit")
				.category("Water")
				.build();
		service.save(register);
		register = Register.builder()
				.status("Pending")
				.dueDate(Calendar.getInstance().getTime())
				.title("Conta de Luz")
				.amount(61.30)
				.type("Debit")
				.category("Light")
				.build();
		service.save(register);
		register = Register.builder()
				.status("Delayed")
				.dueDate(Calendar.getInstance().getTime())
				.title("Conta de Telefone")
				.amount(43.20)
				.type("Debit")
				.category("Phone")
				.build();
		service.save(register);
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
	public User userLoggedIn(){
		return new User().builder()
				.id(UUID.randomUUID().toString())
				.name("Fabiano Góes")
				.login("fabianogoes")
				.password("123")
				.email("fabianogoes@gmail.com")
				.build();

	}


}
