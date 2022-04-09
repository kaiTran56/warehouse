package com.tranquyet.opc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpcMain {

	public static void main(String[] args) {

		SpringApplication.run(OpcMain.class, args);
		System.out.println("OPC MAIN");
	}

}
