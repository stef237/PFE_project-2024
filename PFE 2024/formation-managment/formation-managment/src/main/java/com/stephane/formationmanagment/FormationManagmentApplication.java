package com.stephane.formationmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class FormationManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormationManagmentApplication.class, args);
	}

}
