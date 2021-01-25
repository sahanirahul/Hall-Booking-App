package com.scryanalytics.HallBooking.Model;

public class InsertBookingResponseModel {

	String message;
	HallBookingRequest hallBookingRequest;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HallBookingRequest getHallBookingRequest() {
		return hallBookingRequest;
	}
	public void setHallBookingRequest(HallBookingRequest hallBookingRequest) {
		this.hallBookingRequest = hallBookingRequest;
	}
	
}
