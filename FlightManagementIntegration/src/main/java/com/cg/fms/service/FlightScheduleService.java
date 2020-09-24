package com.cg.fms.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.ScheduledFlightsDao;
import com.cg.fms.dto.FlightSchedule;

@Service
@Transactional
public class FlightScheduleService implements IFlightSchedule {

	@Autowired
	ScheduledFlightsDao flightScheduleDao;

	// Searching for Flights
	@Override
	public List<FlightSchedule> getScheduleFlights(String fromLocation, String toLocation, Date date) {
		List<FlightSchedule> availableFlights = new ArrayList<>();
		// Storing all scheduled flights data
		List<FlightSchedule> flightList = flightScheduleDao.findAll();
		// Filtering results with given input
		for (FlightSchedule flightRoute : flightList)
			if (flightRoute.getSource().getArea().equalsIgnoreCase(fromLocation)
					&& flightRoute.getDestination().getArea().equalsIgnoreCase(toLocation)
					&& flightRoute.getDeparture().toLocalDate().equals(date.toLocalDate())) {
				availableFlights.add(flightRoute);
			}

		return availableFlights;

	}
	
	
	
	    //To add FlightSchedule	
		@Override
		public FlightSchedule addFlightSchedule(FlightSchedule flight_Schedule)
	    {
			 return flightScheduleDao.save(flight_Schedule);
		}
		
	    //To view All FlightSchedules	
		@Override
		public List<FlightSchedule> viewFlightSchedules()
	    {	
				return flightScheduleDao.findAll();
		}

		//To update FlightSchedule By Id
		@Override
		public void updateFlightSchedule(FlightSchedule flight_Schedule)
		{
			FlightSchedule f = flightScheduleDao.findById(flight_Schedule.getScheduleId()).get();
			f.setFare(flight_Schedule.getFare());
			f.setArrival(flight_Schedule.getArrival());
			f.setDeparture(flight_Schedule.getDeparture());
		}

		//To view FlightSchedule By Id
		@Override
		public Optional<FlightSchedule> viewFlightSchedule(String scheduleId) 
		{
			return flightScheduleDao.findById(scheduleId);
		}

		//To delete FlightSchedule By Id
		@Override
		public void deleteFlightSchedule(String scheduleId) {

			flightScheduleDao.deleteById(scheduleId);
			
		}

}
