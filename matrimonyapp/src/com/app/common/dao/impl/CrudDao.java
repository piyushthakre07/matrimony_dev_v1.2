package com.app.common.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.common.dao.ICrudDao;
@Repository
public class CrudDao implements ICrudDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean save(Object t) {
		try{
			Session session=sessionFactory.getCurrentSession();
			session.save(t);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
