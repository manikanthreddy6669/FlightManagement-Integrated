package com.cg.fms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.BookingDao;
import com.cg.fms.dao.ScheduledFlightsDao;
import com.cg.fms.dto.Booking;
import com.cg.fms.dto.FlightSchedule;

@Service
@Transactional
public class BookingService implements IBooking {

	@Autowired
	BookingDao bookingdao;

	@Autowired
	ScheduledFlightsDao scheduleflights;

	// Add Booking details
	@Override
	public Booking makeBooking(Booking bookingDetails) {
		
		String scheduleid = bookingDetails.getFlightSchedule().getScheduleId();
		//Getting schedule id from booking details 
		FlightSchedule s = scheduleflights.findById(scheduleid).get();
		//Updating available seats count
		int availableSeats = s.getAvailableSeats() - bookingDetails.getNoOfPassengers();
		s.setAvailableSeats(availableSeats);

		return bookingdao.save(bookingDetails);

	}
	
	@Override
	public boolean cancelTickets(long id) {
		// To check whether given id exists
		boolean found = bookingdao.existsById(id);
		// If exists cancel tickets
		if (found) {
			//Getting previous booking details by booking id
			Booking b=bookingdao.findById(id).get();
			//Getting schedule id from booking details 
			String scheduleid = b.getFlightSchedule().getScheduleId();
			//Getting schedule details
			FlightSchedule s = scheduleflights.findById(scheduleid).get();
			//Adding cancelled seats
			int availableSeats = s.getAvailableSeats() + b.getNoOfPassengers();
			//Updating available seats count
			s.setAvailableSeats(availableSeats);
			bookingdao.deleteById(id);
		}
		return found;
	}


}
