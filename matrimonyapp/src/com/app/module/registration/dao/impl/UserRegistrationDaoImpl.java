package com.app.module.registration.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.Temporary;
import com.app.module.registration.dao.IUserRegistrationDao;

@Repository
public class UserRegistrationDaoImpl implements IUserRegistrationDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Temporary fetchTempInfo(Integer tempId,String contactNumber) {
		Temporary temporary=new Temporary();
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Temporary.class);
			criteria.add(Restrictions.eq("tempId", tempId));
			criteria.add(Restrictions.eq("contactNumber", contactNumber));
			temporary = (Temporary) criteria.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
			// throw e;
		}
		return	 temporary;
	}
	

}
