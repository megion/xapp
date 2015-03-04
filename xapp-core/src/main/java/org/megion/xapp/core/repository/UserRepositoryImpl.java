package org.megion.xapp.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.megion.xapp.core.aop.annotation.MyLog;
import org.megion.xapp.core.entity.Member;
import org.megion.xapp.core.entity.User;
import org.megion.xapp.core.entity.User_;
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
        return em.createQuery(criteria).getResultList();
	}

	@Override
	@MyLog
	public List<User> findAllWithRolesOrderedByUsername() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        //SetJoin<User, Role> rolesJoin =  userRoot.join(User_.roles, JoinType.LEFT);
        userRoot.fetch(User_.roles, JoinType.LEFT);
        
        criteria.select(userRoot).orderBy(cb.asc(userRoot.get(User_.username))).distinct(true);
        return em.createQuery(criteria).getResultList();
	}

	@Override
	public User findById(Long id) {
		return em.find(User.class, id);
	}

}
