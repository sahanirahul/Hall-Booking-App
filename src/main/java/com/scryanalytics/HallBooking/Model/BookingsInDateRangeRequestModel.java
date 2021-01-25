package com.scryanalytics.HallBooking.Model;

import java.sql.Date;

public class BookingsInDateRangeRequestModel {
	public Date startDate;
	public Date endDate;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}
