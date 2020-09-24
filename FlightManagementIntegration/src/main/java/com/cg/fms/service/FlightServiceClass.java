package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.FlightDao;
import com.cg.fms.dto.Flight;


@Service
@Transactional
public class FlightServiceClass implements FlightServiceInterface {
	@Autowired
	FlightDao flightDao;

	@Override
	public Optional<Flight> searchFlight(int flightNumber) {
	return	flightDao.findById(flightNumber);
		
	}

	@Override
	public String addFlight(Flight flight) {
		flightDao.save(flight);
		return "Flight Added into the DataBase Successfuly";
	}

	@Override
	public Flight updateFlight(Flight flightEntity) {
		
		return flightDao.save(flightEntity);
	}

	@Override
	public String deleteFlight(int flightNumber) {
		flightDao.deleteById(flightNumber);
		return "Flight is DELETED SUCCESSFULLY !";
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightDao.findAll();
		 
	}

	

}