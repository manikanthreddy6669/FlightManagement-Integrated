package com.cg.fms.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.cg.fms.dto.FlightSchedule;

public interface IFlightSchedule {

	List<FlightSchedule> getScheduleFlights(String fromLocation, String toLocation, Date date);

	public FlightSchedule addFlightSchedule(FlightSchedule flight_Schedule);

	public void updateFlightSchedule(FlightSchedule flight_Schedule);

	public Optional<FlightSchedule> viewFlightSchedule(String scheduleId);

	public void deleteFlightSchedule(String scheduleId);

	public List<FlightSchedule> viewFlightSchedules();

}
