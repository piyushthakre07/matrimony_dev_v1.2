package com.app.module.userinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.UserInfo;
import com.app.module.userinfo.dao.IUserInfoDao;

@Repository
public class UserInfoDaoImpl implements IUserInfoDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public UserInfo saveUserInfo(UserInfo userInfo) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(userInfo);
			return userInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@Override
	public boolean updateUSerInfo(UserInfo userInfo) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(userInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public UserInfo fetchUserInfo(Integer userinfoId) {
		UserInfo userInfo = new UserInfo();
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserInfo.class);
			criteria.add(Restrictions.eq("active", "Y"));
			criteria.add(Restrictions.eq("userinfoId", userinfoId));
			userInfo = (UserInfo) criteria.uniqueResult();
			return userInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// throw e;
		}

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<UserInfo> fetchAllUserInfo() {
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserInfo.class);
			criteria.add(Restrictions.eq("active", "Y"));
			userInfoList = criteria.list();
			return userInfoList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
