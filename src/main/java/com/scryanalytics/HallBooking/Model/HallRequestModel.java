package com.scryanalytics.HallBooking.Model;

import java.sql.Date;
import java.sql.Time;


public class HallRequestModel {
	
	Date RequestDate;
	Time StartTime;
	Time EndTime;
	int ReqCapacity;
	
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
	public int getReqCapacity() {
		return ReqCapacity;
	}
	public void setReqCapacity(int reqCapacity) {
		ReqCapacity = reqCapacity;
	}
	
}
