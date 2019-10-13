package com.app.module.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.User;
import com.app.module.user.dao.IUserDao;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public User saveUser(User user) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@Override
	public boolean updateUser(User user) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public User fetchUser(Integer userId) {
		User user = new User();
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("active", "Y"));
			criteria.add(Restrictions.eq("userId", userId));
			user = (User) criteria.uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// throw e;
		}

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<User> fetchAllUser() {
		List<User> userList = new ArrayList<User>();
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("active", "Y"));
			userList = criteria.list();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
