package com.storage.s3demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication(exclude = ContextStackAutoConfiguration.class)
public class S3demoApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3demoApplication.class, args);
	}

}
