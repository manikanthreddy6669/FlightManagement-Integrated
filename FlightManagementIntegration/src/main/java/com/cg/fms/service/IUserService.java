package com.cg.fms.service;

import com.cg.fms.dto.Users;

public interface IUserService {

	Users loginCheck(int userid, String password);

	Users registerDetails(Users user);

	//List<Booking> getBookingList();
}
