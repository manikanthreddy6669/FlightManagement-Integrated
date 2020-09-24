package com.cg.fms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.Exception.Flight_Schedule_NotFoundException;
import com.cg.fms.dao.ScheduledFlightsDao;
import com.cg.fms.dto.FlightSchedule;
import com.cg.fms.service.IFlightSchedule;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
public class AdminScheduleController {
	
	@Autowired
	private IFlightSchedule flight_Scheduling_Service;
	
	@Autowired
	private ScheduledFlightsDao flight_Scheduling_DAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminScheduleController.class);

	
	   //add FlightSchedule	
	   @PostMapping("/addFlightSchedule")
	   public ResponseEntity<String> addFlightSchedule( @RequestBody FlightSchedule flight_Schedule)
		{
	
			try {
				flight_Scheduling_Service.addFlightSchedule(flight_Schedule);
				LOGGER.info("Add Flight Schedule method is accessed");
				return new ResponseEntity<>("Flight Schedule details added",HttpStatus.OK);
			} 
			catch (DataIntegrityViolationException ex) {
				return new ResponseEntity<>(ex.getMessage() + "Flight Schedule details already added", HttpStatus.BAD_REQUEST);
			}
		}
		
		//update Flight schedule
		@PutMapping("/updateFlightSchedule/{id}")
		public ResponseEntity<Object> updateFlightSchedule(@PathVariable("id") String scheduleId, @RequestBody FlightSchedule flight_Scheduling)throws Flight_Schedule_NotFoundException
		{
			if (flight_Scheduling_DAO.existsById(scheduleId))
			{
				flight_Scheduling_Service.updateFlightSchedule(flight_Scheduling);
				LOGGER.info("update Flight Schedule method is accessed");
				return new ResponseEntity<>("Flight Schedule is updated successsfully", HttpStatus.OK);
			}
			else
			{
				throw new Flight_Schedule_NotFoundException();
			}
		}
		

		//view Flight Schedule By Id
		@GetMapping("/viewFlightSchedule/{id}")
		public ResponseEntity<Object> viewFlightSchedule(@PathVariable("id") String scheduleId)
		{
		    Optional<FlightSchedule> flightSchedule= flight_Scheduling_DAO.findById(scheduleId);
		    if (flightSchedule.isPresent())
		    {
		      LOGGER.info("View Flight Schedule method is accessed");	
		      return new ResponseEntity<>(flightSchedule.get(), HttpStatus.OK);
		    }
		    else
		    {
		      return new ResponseEntity<>("Flight Schedule not found",HttpStatus.NOT_FOUND);
		    }
		 }
		
		
		//view FlightSchedules
		@GetMapping("/viewFlightSchedule")
		public ResponseEntity<Object> viewFlightSchedules()
		{
			List<FlightSchedule> flightScheduleList = flight_Scheduling_Service.viewFlightSchedules();
			return new ResponseEntity<>(flightScheduleList, HttpStatus.OK);
		}

		
		//Delete Flight Schedule By Id
		@DeleteMapping("/deleteFlightSchedule/{id}")
		public ResponseEntity<Object> deleteFlightSchedule(@PathVariable("id") String scheduleId)throws Flight_Schedule_NotFoundException
		{
			if (flight_Scheduling_DAO.existsById(scheduleId))
			{
				flight_Scheduling_Service.deleteFlightSchedule(scheduleId);
				LOGGER.info("Delete Flight Schedule method is accessed");
				return new ResponseEntity<>("Flight Schedule is deleted successsfully", HttpStatus.OK);
			}
			else
			{
				throw new Flight_Schedule_NotFoundException();
			}
		}
}