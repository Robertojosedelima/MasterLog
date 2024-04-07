package br.com.MasterLog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3001")
public class MasterLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterLogApplication.class, args);
		
		
	}

}
