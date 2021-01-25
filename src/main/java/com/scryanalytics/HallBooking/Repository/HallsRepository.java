package com.scryanalytics.HallBooking.Repository;

import org.springframework.data.repository.CrudRepository;

import com.scryanalytics.HallBooking.Model.Halls;

public interface HallsRepository extends CrudRepository<Halls, Integer> {

}
