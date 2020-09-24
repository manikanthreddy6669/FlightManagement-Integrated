package com.cg.fms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.Exception.FlightNotFoundException;
import com.cg.fms.Exception.ObjectNullException;
import com.cg.fms.dto.Flight;
import com.cg.fms.service.FlightServiceInterface;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = "*")
public class AdminFlightController {

	@Autowired
	private FlightServiceInterface flightService;

	@PostMapping("/addflight") // Mapping the URL to add flight
	public ResponseEntity<String> addFlight(@RequestBody Flight flight) {

		// Checking whether the received object is null
		if (flight.getCarrierName() == null || flight.getFlightModel() == null || flight.getSeatCapacity() == 0)
			throw new ObjectNullException("Object Cannot be Empty"); // If object is null throwing an Null pointer
																		// Exception

		// Checking whether this flight is already available in the database by
		// seaechFlight() method
		Optional<Flight> flightEntity = flightService.searchFlight(flight.getFlightNo());
		if (flightEntity.isPresent())
			return new ResponseEntity<String>("Flight with " + flight.getFlightNo() + " number is already added",
					HttpStatus.ALREADY_REPORTED);
		// Adding the flight into the Database
		String msg = flightService.addFlight(flight);
		// Returning the ResponseEntity<String> with Httpstatus and message
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@GetMapping("/searchflight/{flightNumber}") // Mapping the URL for Searching Flight

	// @PathVariable is used to extract the variable from the URL

	public Optional<Flight> searchFlight(@PathVariable int flightNumber) {
		if (flightNumber == 0)
			throw new ObjectNullException("Flight Number Cannot be 0 !");

		Optional<Flight> flightEntity = flightService.searchFlight(flightNumber);
		if (flightEntity.isPresent()) {
			return flightEntity;
		}

		throw new FlightNotFoundException("Flight with " + flightNumber + " Flight Number is NOT AVAILABLE !!!"); // Throwing
																													// exception
																													// available

	}

	@PutMapping("/updateflight") // Mapping the URL to update flight

	// @RequestBody is used to get the object of the class from the URL

	public String updateFlight(@RequestBody Flight flightEntity) {
		if (flightEntity.getCarrierName() == null || flightEntity.getFlightModel() == null
				|| flightEntity.getSeatCapacity() == 0 || flightEntity.getFlightNo() == 0) // Checking whether the
																							// object received is
																							// null

			throw new ObjectNullException("Object Cannot be NULL!");
		Optional<Flight> flight_check = flightService.searchFlight(flightEntity.getFlightNo());
		if (flight_check.isPresent()) { // checking whether values are present is received object
			flightService.updateFlight(flightEntity);
			return "Successfully UPDATED ! !";
		}
		return "Flight with " + flightEntity.getFlightNo() + " is not AVAILABLE to update!!";

	}

	@DeleteMapping("/deleteflight/{flightNumber}") // Mapping the URL to delete the flight with flight number

	public String deleteFlight(@PathVariable int flightNumber) {
		if (flightNumber == 0)
			throw new ObjectNullException("Flight Number Cannot be 0 !");

		// To delete first i am searching whether the flight is available in the
		// database
		Optional<Flight> flightEntity = flightService.searchFlight(flightNumber);
		if (flightEntity.isPresent()) {
			// deleting the flight with particular flight number
			return flightService.deleteFlight(flightNumber);

		}
		// throwing exception if flight is not available
		throw new FlightNotFoundException("Flight with " + flightNumber + " Flight Number is NOT AVAILABLE !!!");

	}

	@GetMapping("/allflights") // Mapping the URL to get all the flights available
	public ResponseEntity<List<Flight>> viewAllFlights() {
		List<Flight> all_flights = flightService.getAllFlights();
		if (all_flights.isEmpty()) { // Checking whether the list is Empty

			// Throwing exception if the object is empty

			throw new FlightNotFoundException("Flight Not found");
		}

		// Else returning the List of available flights with HtttpStatus

		return new ResponseEntity<List<Flight>>(all_flights, HttpStatus.OK);

	}
}