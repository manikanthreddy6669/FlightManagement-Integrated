package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fms.dto.Passengers;

public interface PassengerDao extends JpaRepository<Passengers, String> {

}
