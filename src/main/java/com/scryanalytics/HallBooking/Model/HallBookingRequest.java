package com.scryanalytics.HallBooking.Model;

import java.sql.Date;
import java.sql.Time;


import org.springframework.data.annotation.Id;

public class HallBookingRequest {

	@Id
	long RequestId;
	String RequestorId;
	String RequestorName;
	Date RequestDate;
	Time StartTime;
	Time EndTime;
	String HallName;
	int ReqCapacity;
	int Capacity;

	public long getRequestId() {
		return RequestId;
	}

	public void setRequestId(long requestId) {
		RequestId = requestId;
	}

	public String getRequestorId() {
		return RequestorId;
	}

	public void setRequestorId(String requestorId) {
		RequestorId = requestorId;
	}

	public String getRequestorName() {
		return RequestorName;
	}

	public void setRequestorName(String requestorName) {
		RequestorName = requestorName;
	}

	public Date getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(Date requestDate) {
		RequestDate = requestDate;
	}

	public Time getStartTime() {
		return StartTime;
	}

	public void setStartTime(Time startTime) {
		StartTime = startTime;
	}

	public Time getEndTime() {
		return EndTime;
	}

	public void setEndTime(Time endTime) {
		EndTime = endTime;
	}

	public String getHallName() {
		return HallName;
	}

	public void setHallName(String hallName) {
		HallName = hallName;
	}

	public int getReqCapacity() {
		return ReqCapacity;
	}

	public void setReqCapacity(int reqCapacity) {
		ReqCapacity = reqCapacity;
	}

	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int capacity) {
		Capacity = capacity;
	}

}
