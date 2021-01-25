package com.scryanalytics.HallBooking.Model;

import org.springframework.data.annotation.Id;

public class Halls {

	@Id
	public int id;
	public String hallName;
	public int capacity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
