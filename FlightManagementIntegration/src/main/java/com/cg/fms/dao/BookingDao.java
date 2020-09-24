package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fms.dto.Booking;

public interface BookingDao extends JpaRepository<Booking, Long> {

}
