package com.scryanalytics.HallBooking.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scryanalytics.HallBooking.Mapper.Mappers;
import com.scryanalytics.HallBooking.Model.AvailableBookingResponse;
import com.scryanalytics.HallBooking.Model.BookingsInDateRangeRequestModel;
import com.scryanalytics.HallBooking.Model.HallBookingRequest;
import com.scryanalytics.HallBooking.Model.HallRequestModel;
import com.scryanalytics.HallBooking.Model.InsertBookingResponseModel;
import com.scryanalytics.HallBooking.Repository.HallBookingRequestRepository;

@Service
public class HallBookingRequestService {

	@Autowired
	private HallBookingRequestRepository hallBookingRequestRepo;

	/*
	 * public HallBookingRequestService(HallBookingRequestRepository
	 * hallBookingRequestRepo) { this.hallBookingRequestRepo =
	 * hallBookingRequestRepo; }
	 */

	public InsertBookingResponseModel insertRequest(HallBookingRequest hbr) {
		hbr.setCapacity(UpdateHallsFromDBService.mapOfHallsPresentInDB.get(hbr.getHallName()));
		HashMap<String, Integer> mapOfAvailableHalls = this
				.getAvailableHalls(Mappers.mapHallBookingRequestToHallRequestModel(hbr)).getAvailableHalls();
		if (mapOfAvailableHalls.containsKey(hbr.getHallName())) {
			HallBookingRequest insertRes = hallBookingRequestRepo.save(hbr);
			InsertBookingResponseModel res = new InsertBookingResponseModel();
			String message = "Booking Request Successful";
			res.setMessage(message);
			res.setHallBookingRequest(insertRes);
			return res;
		} else {
			InsertBookingResponseModel errorRes = new InsertBookingResponseModel();
			String message = "Requested Hall not available. Please try an available hall or different date/time";
			errorRes.setMessage(message);
			errorRes.setHallBookingRequest(hbr);
			return errorRes;
		}

	}

	public AvailableBookingResponse getAvailableHalls(HallRequestModel hallRequestModel) {

		List<HallBookingRequest> bookedHalls = this.getBookedHall(hallRequestModel);
		HashMap<String, Integer> availableHalls = new HashMap<String, Integer>();
		availableHalls.putAll(UpdateHallsFromDBService.mapOfHallsPresentInDB);
		for (HallBookingRequest bookedHall : bookedHalls) {
			availableHalls.remove(bookedHall.getHallName());
		}
		List<String> keySet = new ArrayList<String>();
		keySet.addAll(availableHalls.keySet());
		for (String hall : keySet) {
			if (availableHalls.get(hall) < hallRequestModel.getReqCapacity())
				availableHalls.remove(hall);
		}

		AvailableBookingResponse res = new AvailableBookingResponse();
		res.setAvailableHalls(availableHalls);
		res.setMessage(availableHalls.keySet().size() + " Halls Available for given request date,time and capacity");
		return res;

	}

	public List<HallBookingRequest> getBookedHall(HallRequestModel hrm) {
		return hallBookingRequestRepo.findBookedHall(hrm.getRequestDate(), hrm.getStartTime(), hrm.getEndTime());
	}

	public Iterable<HallBookingRequest> getHallBookingRequests() {
		return hallBookingRequestRepo.findAll();
	}

	public List<HallBookingRequest> getHallBookingRequestsInDateRange(BookingsInDateRangeRequestModel req) {
		if (req.getEndDate() == null) {
			Date date = new Date();
			String modifiedDate = new SimpleDateFormat("YYYY-MM-DD").format(date);
			java.sql.Date endDate=java.sql.Date.valueOf(modifiedDate);
			req.setEndDate(endDate);
		}
		return hallBookingRequestRepo.findBookingBetweenDates(req.getStartDate(), req.getEndDate());
	}
}
