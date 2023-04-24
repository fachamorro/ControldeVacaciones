package gob.distrito05d03salud.controlvacaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ControlvacacionesApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ControlvacacionesApplication.class, args);
	}

}
