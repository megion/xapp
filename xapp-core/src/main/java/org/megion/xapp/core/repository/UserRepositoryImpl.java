package org.megion.xapp.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.megion.xapp.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
    private EntityManager em;

	@Override
	public List<User> findAllOrderedByUsername() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> member = criteria.from(User.class);
        criteria.select(member).orderBy(cb.asc(member.get("username")));
        List<User> users = em.createQuery(criteria).getResultList();
        System.out.println("user[0].roles: " + users.get(0).getRoles());
        return users;
	}

}
