package org.megion.xapp.core.repository;

import java.util.List;

import org.megion.xapp.core.aop.annotation.MyLog;
import org.megion.xapp.core.entity.User;

public interface UserRepository {
	
    List<User> findAllOrderedByUsername();
    
    /**
     * @return ordered by name users list with fetched roles
     */
    List<User> findAllWithRolesOrderedByUsername();
    
    User findById(Long id);
}
