package com.scryanalytics.HallBooking.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.scryanalytics.HallBooking.Model.AvailableBookingResponse;
import com.scryanalytics.HallBooking.Model.BookingsInDateRangeRequestModel;
import com.scryanalytics.HallBooking.Model.HallBookingRequest;
import com.scryanalytics.HallBooking.Model.HallRequestModel;
import com.scryanalytics.HallBooking.Model.InsertBookingResponseModel;
import com.scryanalytics.HallBooking.Repository.HallBookingRequestRepository;
import com.scryanalytics.HallBooking.Service.HallBookingRequestService;
import com.scryanalytics.HallBooking.Service.UpdateHallsFromDBService;
import com.scryanalytics.HallBooking.Util.HallBookingUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/")
public class HallBookingController {

	private HallBookingRequestService hallBookingRequestService;

	public HallBookingController(HallBookingRequestService hallBookingRequestService) {
		this.hallBookingRequestService = hallBookingRequestService;
	}

	@RequestMapping("/")
	public String instructions() {

		try {
			/*
			 * FileInputStream inputStream = null; ClassLoader classLoader =
			 * this.getClass().getClassLoader(); File configFile = new
			 * File(classLoader.getResource("instructions.txt").getFile());
			 * 
			 * inputStream = new FileInputStream(configFile); BufferedReader reader = new
			 * BufferedReader(new InputStreamReader(inputStream)); StringBuilder
			 * stringBuilder = new StringBuilder(); String line = null; String ls =
			 * System.getProperty("line.separator"); while ((line = reader.readLine()) !=
			 * null) { stringBuilder.append(line); stringBuilder.append("\n"); } // delete
			 * the last new line separator stringBuilder.deleteCharAt(stringBuilder.length()
			 * - 1); reader.close();
			 * 
			 * String content = stringBuilder.toString();
			 */
			String response = "Welcome to Hall Booking App! \n Use the following API end points : "
					+ "\n 1) /getAllBookings    | to get all bookings                                                | GetRequest   | No params/request body "
					+ "\n 2) /getBookings       | to get bookings in a date range                                    | PostRequest  | request body -> startDate, endDate"
					+ "\n 3) /hallAvailability  | to get available halls for a given date,time and Request Capacity  | PostRequest  | request body -> requestDate, startTime, endTime, reqCapacity"
					+ "\n 4) /requestBooking    | to book a hall for a given date,time and capacity                  | PostRequest  | request body -> requestorId, requestorName, requestDate, startTime, endTime, reqCapacity, hallName";
			return response;
		} catch (Exception e) {
			return "Exception : " + e;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/requestBooking")
	public InsertBookingResponseModel insertRequest(@RequestBody HallBookingRequest req) {
		try {
			Timestamp now = new Timestamp(new java.util.Date().getTime());
			Timestamp request = HallBookingUtil.convertSqlDateToTimestamp(req.getRequestDate(), req.getStartTime());
			if (HallBookingUtil.isXAfterY(now, request)) {
				InsertBookingResponseModel errorRes = new InsertBookingResponseModel();
				String message = "Request Date/Time cannot be in the past, please provide a valid request date";
				errorRes.setMessage(message);
				errorRes.setHallBookingRequest(req);
				return errorRes;
			} else
				return hallBookingRequestService.insertRequest(req);

		} catch (Exception e) {
			InsertBookingResponseModel errorRes = new InsertBookingResponseModel();
			String message = "Exception while booking request : " + e;
			errorRes.setMessage(message);
			errorRes.setHallBookingRequest(req);
			return errorRes;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/hallAvailability")
	public AvailableBookingResponse hallAvailability(@RequestBody HallRequestModel req) {
		try {
			Timestamp now = new Timestamp(new java.util.Date().getTime());
			Timestamp request = HallBookingUtil.convertSqlDateToTimestamp(req.getRequestDate(), req.getStartTime());
			if (HallBookingUtil.isXAfterY(now, request)) {
				AvailableBookingResponse res = new AvailableBookingResponse();
				String message = "Request Date/Time cannot be in the past, please provide a valid request date : ";
				res.setMessage(message);
				return res;
			} else
				return hallBookingRequestService.getAvailableHalls(req);
		} catch (Exception e) {
			AvailableBookingResponse res = new AvailableBookingResponse();
			res.setMessage("Exception occured while checking hall availability, please try again : " + e);
			return res;
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/getAllBookings")
	public Iterable<HallBookingRequest> getHallBookingRequests() {
		try {
			return hallBookingRequestService.getHallBookingRequests();
		} catch (Exception e) {
			return null;
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/getBookings")
	public List<HallBookingRequest> getHallBookingsBetweenTimePeriod(@RequestBody BookingsInDateRangeRequestModel req) {
		try {
			return hallBookingRequestService.getHallBookingRequestsInDateRange(req);
		} catch (Exception e) {
			return null;
		}
	}

}