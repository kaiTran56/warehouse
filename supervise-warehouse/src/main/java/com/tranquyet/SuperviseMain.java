package com.tranquyet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@EntityScan(basePackages = { "com.tranquyet.common.entities", "com.tranquyet.sup.entities",
		"com.tranquyet.sup.product_managements.entities", "com.tranquyet.sup.system_settings.entities",
		"com.tranquyet.sup.operations.entities", "com.tranquyet.sup.dashboard.entities",
		"com.tranquyet.sup.statistics.entities" })
@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class SuperviseMain extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SuperviseMain.class, args);
	}

}
