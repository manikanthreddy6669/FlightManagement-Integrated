package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import com.cg.fms.dto.Flight;

public interface FlightServiceInterface {

	Optional<Flight> searchFlight(int flightNumber);

	String addFlight(Flight flight);

	Flight updateFlight(Flight flightEntity);

	String deleteFlight(int flightNumber);

	List<Flight> getAllFlights();

	

}