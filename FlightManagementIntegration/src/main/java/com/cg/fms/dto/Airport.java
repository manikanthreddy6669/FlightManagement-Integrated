package com.cg.fms.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Airport {
	@Id
	@Column(name = "airport_id")
	private String airportId;
	@Column(name = "airport_name")
	private String airportName;
	@Column(name = "airport_loc")
	private String area;

	public Airport() {
		super();
	}

	public Airport(String airportId, String airportName, String area) {
		this.airportId = airportId;
		this.airportName = airportName;
		this.area = area;
	}

	public String getAirportId() {
		return airportId;
	}

	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}