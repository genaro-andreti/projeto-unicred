package br.com.unicred.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class ProjetoUnicredApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ProjetoUnicredApplication.class);
	
	@Value("${msg.inicial}")
	private String msgInicial;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoUnicredApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			log.info(this.msgInicial);
		};
	}

}
