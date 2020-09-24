package com.cg.fms.Exception;

public class Flight_Schedule_NotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	public Flight_Schedule_NotFoundException()
	{
		super();
	}

	public Flight_Schedule_NotFoundException(String message) 
	{
		super(message);
	}

}