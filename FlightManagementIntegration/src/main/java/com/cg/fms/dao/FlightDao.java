package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fms.dto.Flight;

public interface FlightDao extends JpaRepository<Flight, Integer>{

}