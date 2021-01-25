package com.scryanalytics.HallBooking.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.scryanalytics.HallBooking.Model.Halls;
import com.scryanalytics.HallBooking.Repository.HallsRepository;
import com.scryanalytics.HallBooking.Service.UpdateHallsFromDBService;

@Configuration
public class HallBookingConfiguration {

	Environment env;
	@Autowired
	HallsRepository hallsRepo;
	
	private final String URL = "url";
	private final String USER = "dbuser";
	private final String DRIVER = "driver";
	private final String PASSWORD = "dbpassword";

	
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(env.getProperty(URL));
		driverManagerDataSource.setUsername(env.getProperty(USER));
		driverManagerDataSource.setPassword(env.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(env.getProperty(DRIVER));
		return driverManagerDataSource;
	}
	
	//@Bean
	JdbcTemplate getJdbcTemplate()
	{
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public void getHallsListFromDB()
	{		
		Iterable<Halls> hallsPresentInDB = hallsRepo.findAll();
		for(Halls hall:hallsPresentInDB)
		{
			UpdateHallsFromDBService.mapOfHallsPresentInDB.put(hall.getHallName(), hall.getCapacity());
		}
	}

}