package cn.cloud.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class LogisticsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
		
	}
	


}
