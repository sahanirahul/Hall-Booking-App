package com.scryanalytics.HallBooking.Model;

import java.util.HashMap;

public class AvailableBookingResponse {

	private String message;
	private HashMap<String,Integer> availableHallsWithCapacity = new HashMap<String,Integer>();

	public HashMap<String, Integer> getAvailableHalls() {
		return this.availableHallsWithCapacity;
	}

	public void setAvailableHalls(HashMap<String, Integer> availableHallsWithCapacity) {
		this.availableHallsWithCapacity = availableHallsWithCapacity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
