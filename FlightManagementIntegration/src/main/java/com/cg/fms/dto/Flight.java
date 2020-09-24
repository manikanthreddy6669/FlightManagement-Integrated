package com.cg.fms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Flight {
	@Id
	@Column(name = "flight_no")
	private int flightNo;
	@Column(name = "carrier_name")
	private String carrierName;
	@Column(name = "seat_capacity")
	private int seatCapacity;
	@Column
	private String flightModel;

	public Flight() {
	}

	public Flight(int flightNo, String carrierName, int seatCapacity, String flightModel) {
		super();
		this.flightNo = flightNo;
		this.carrierName = carrierName;
		this.seatCapacity = seatCapacity;
		this.flightModel = flightModel;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public int getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	public String getFlightModel() {
		return flightModel;
	}

	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}

}