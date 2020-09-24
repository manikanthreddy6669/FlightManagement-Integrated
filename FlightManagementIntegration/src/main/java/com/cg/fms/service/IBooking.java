package com.cg.fms.service;

import com.cg.fms.dto.Booking;

public interface IBooking {
	
	public Booking makeBooking(Booking bookingDetails);
	
	boolean cancelTickets(long id);


}
