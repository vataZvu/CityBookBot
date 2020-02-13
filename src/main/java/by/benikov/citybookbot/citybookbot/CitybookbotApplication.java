package by.benikov.citybookbot.citybookbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class CitybookbotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(CitybookbotApplication.class, args);
	}

}
