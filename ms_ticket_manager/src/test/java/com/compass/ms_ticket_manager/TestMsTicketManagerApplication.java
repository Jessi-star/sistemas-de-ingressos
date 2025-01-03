package com.compass.ms_ticket_manager;

import org.springframework.boot.SpringApplication;

public class TestMsTicketManagerApplication {

	public static void main(String[] args) {
		SpringApplication.from(MsTicketManagerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
