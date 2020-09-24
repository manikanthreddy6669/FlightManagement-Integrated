package com.cg.fms.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.fms.dto.Airport;
import com.cg.fms.dto.FlightSchedule;

public interface ScheduledFlightsDao extends JpaRepository<FlightSchedule, String> {

	@Query("select r from FlightSchedule r where r.source=?1 and r.destination=?2 and date(r.departure)=?3")
	List<FlightSchedule> getScheduleFlights(Airport fromLocation, Airport toLocation, Date date);
	
}
