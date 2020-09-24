package com.cg.fms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.UserDao;
import com.cg.fms.dto.Users;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	UserDao userdao;
	
	//Check Login Details
	@Override
	public Users loginCheck(int userid, String password) {
		return userdao.loginCheck(userid, password);
		
	}

	// Register user
	@Override
	public Users registerDetails(Users user)
	{
	return userdao.save(user);
	}


//	@Override
//	public List<Booking> getBookingList() {
//		return bookingdao.findAll(Sort.by(Sort.Direction.DESC, "bookingId"));
//	}

}
