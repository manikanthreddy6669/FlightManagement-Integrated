package com.cg.fms.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bookingdetails")
public class Booking {
	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingseq")
	@SequenceGenerator(name = "bookingseq", sequenceName = "bookingdetails_seq", allocationSize = 1)
	private long bookingId;

	@OneToOne
	@JoinColumn(name = "userId")
	private Users users;

	@OneToOne
	@JoinColumn(name = "sched_id")
	private FlightSchedule flightSchedule;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id")
	private List<Passengers> passengers;

	@Column(name = "booking_date")
	private LocalDate bookingDate;

	private int noOfPassengers;

	public Booking() {
	}

	public Booking(long bookingId, Users users, FlightSchedule flightSchedule, List<Passengers> passengers,
			LocalDate bookingDate, int noOfPassengers) {
		super();
		this.bookingId = bookingId;
		this.users = users;
		this.flightSchedule = flightSchedule;
		this.passengers = passengers;
		this.bookingDate = bookingDate;
		this.noOfPassengers = noOfPassengers;
	}

	public Booking(long bookingId) {
		this.bookingId = bookingId;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public FlightSchedule getFlightSchedule() {
		return flightSchedule;
	}

	public void setFlightSchedule(FlightSchedule flightSchedule) {
		this.flightSchedule = flightSchedule;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public List<Passengers> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passengers> passengers) {
		this.passengers = passengers;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

}