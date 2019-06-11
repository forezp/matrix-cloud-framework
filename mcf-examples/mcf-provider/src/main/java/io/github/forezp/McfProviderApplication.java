package io.github.forezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class McfProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(McfProviderApplication.class, args);
	}

}
