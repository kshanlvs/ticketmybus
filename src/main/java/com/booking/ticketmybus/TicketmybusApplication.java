package com.booking.ticketmybus;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class TicketmybusApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(TicketmybusApplication.class, args);
    }

    @PostConstruct
    public void postConstruct(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
