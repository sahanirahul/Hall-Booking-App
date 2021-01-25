package com.scryanalytics.HallBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.scryanalytics.HallBooking.Util.ApplicationContextLoader;

@SpringBootApplication
public class HallBookingApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(HallBookingApplication.class, args);
		ApplicationContextLoader.setAppContext(appContext);
	}

}
