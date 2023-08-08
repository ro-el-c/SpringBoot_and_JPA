package jpa.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {
	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.setData("hello JPA shop");
		String data = hello.getData();
		System.out.println("data = " + data);

		SpringApplication.run(ShopApplication.class, args);
	}

}
