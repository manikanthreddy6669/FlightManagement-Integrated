package com.cg.fms.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = FlightNotFoundException.class)
	public ResponseEntity<Object> exception(FlightNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ObjectNullException.class)
	public ResponseEntity<Object> exception(ObjectNullException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { Flight_Schedule_NotFoundException.class })
	public ResponseEntity<Object> exception(Flight_Schedule_NotFoundException exception) {
		return new ResponseEntity<>("Flight Schedule not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FlightsNotFound.class)
	public ResponseEntity<String> flightsNotFound(FlightsNotFound e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
	}

}
