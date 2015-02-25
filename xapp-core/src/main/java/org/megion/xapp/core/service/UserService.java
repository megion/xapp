package org.megion.xapp.core.service;

import java.util.List;

import org.megion.xapp.core.entity.User;

public interface UserService {
	List<User> findAllOrderedByUsername();
}
