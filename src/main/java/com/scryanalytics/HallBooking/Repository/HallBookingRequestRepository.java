package com.scryanalytics.HallBooking.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scryanalytics.HallBooking.Model.HallBookingRequest;

@Repository
public interface HallBookingRequestRepository extends CrudRepository<HallBookingRequest, Long> {
	/*
	 * @Modifying
	 * 
	 * @Query("INSERT INTO HALL_BOOKING_REQUEST SET name = :name WHERE id = :id")
	 * boolean updateName(@Param("id") Long id, @Param("name") String name);
	 * 
	 * @Modifying
	 * 
	 * @Query("INSERT INTO HALL_BOOKING_REQUEST SET name = :name WHERE id = :id")
	 * boolean insertRequest(@Param("requestor_id") String
	 * requestor_id, @Param("requestor_name") String requestor_name);
	 */
	//SELECT * FROM HALL_BOOKING_REQUEST WHERE request_date = '2021-01-25' and start_time >= '09:00:00' and end_time <= '12:00:00'
	@Query("SELECT * FROM HALL_BOOKING_REQUEST WHERE request_date = :request_date and start_time >= :start_time and end_time <= :end_time")
	 List<HallBookingRequest> findBookedHall(@Param("request_date") Date request_date, @Param("start_time") Time start_time, @Param("end_time") Time end_time);
	
	@Query("SELECT * FROM HALL_BOOKING_REQUEST WHERE request_date >=:start_date and request_date <= :end_date")
	 List<HallBookingRequest> findBookingBetweenDates(@Param("start_date") Date start_date, @Param("end_date") Date end_date);
}
