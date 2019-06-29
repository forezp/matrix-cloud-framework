package io.github.forezp;

import com.netflix.loadbalancer.IRule;
import io.github.forezp.ribbonrule.McfRouteRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McfRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(McfRouteApplication.class, args);
	}


}
