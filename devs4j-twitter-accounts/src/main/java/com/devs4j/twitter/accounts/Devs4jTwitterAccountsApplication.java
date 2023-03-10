package com.devs4j.twitter.accounts;

import java.text.Normalizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devs4j.twitter.accounts.service.AccountService;

@SpringBootApplication
public class Devs4jTwitterAccountsApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Devs4jTwitterAccountsApplication.class);

	@Autowired
	private AccountService service;

	public static void main(String[] args) {
		SpringApplication.run(Devs4jTwitterAccountsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//initialize();
	}
	
	/*
	private void initialize() {
		Faker feku = new Faker(new Locale("es-ES"));

		List<String> list = new ArrayList<String>();
		service.deleteAll();

		for (int i = 0; i < 20; i++) {
			Name person = feku.name();

			String firstName = person.firstName();
			String lastName = person.lastName();
			String username = firstName.substring(0, 1).toLowerCase() + lastName.toLowerCase();
			AccountDto build = AccountDto.builder().firstname(firstName).lastname(lastName)
					.username(cleanString(username))
					.password("devs4j")
					.build();


			log.info("first name: {} last name: {}", firstName, lastName);

			AccountDto saved = service.save(build);
			
			for (String id : list) {
				service.follow(saved.getId(), id);
			}
			
			list.add(saved.getId());

		}

	}*/

	public static String cleanString(String texto) {
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return texto;
	}

}
