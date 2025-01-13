package es.mariana.sanz.demo;

import es.mariana.sanz.demo.controller.DemoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("hola mundo");
		SpringApplication.run(DemoApplication.class, args);
		DemoController demoController = new DemoController();
		demoController.hello("satanas vendra a por ti ");
	}

}
