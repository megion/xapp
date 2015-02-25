package org.megion.xapp.core.repository;

import java.util.List;

import org.megion.xapp.core.entity.User;

public interface UserRepository {
	
    List<User> findAllOrderedByUsername();
}
