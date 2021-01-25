package com.scryanalytics.HallBooking.Mapper;

import com.scryanalytics.HallBooking.Model.HallBookingRequest;
import com.scryanalytics.HallBooking.Model.HallRequestModel;

public class Mappers {
	public static HallRequestModel mapHallBookingRequestToHallRequestModel(HallBookingRequest hbr)
	{
		HallRequestModel hrm =  new HallRequestModel();
		hrm.setEndTime(hbr.getEndTime());
		hrm.setStartTime(hbr.getStartTime());
		hrm.setRequestDate(hbr.getRequestDate());
		hrm.setReqCapacity(hbr.getReqCapacity());
		return hrm;

	}
}
