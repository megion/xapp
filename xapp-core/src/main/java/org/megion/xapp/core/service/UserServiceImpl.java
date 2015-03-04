package org.megion.xapp.core.service;

import java.util.List;

import org.megion.xapp.core.aop.annotation.MyLog;
import org.megion.xapp.core.entity.User;
import org.megion.xapp.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	@MyLog
	public List<User> findAllOrderedByUsername() {
		List<User> users = userRepository.findAllOrderedByUsername();
		return users;
	}

}
