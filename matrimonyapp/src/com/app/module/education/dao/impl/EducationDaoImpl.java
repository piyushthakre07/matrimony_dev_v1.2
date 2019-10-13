package com.app.module.education.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Education;
import com.app.module.education.dao.IEducationDao;

@Repository
public class EducationDaoImpl implements IEducationDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public Boolean insertEducation(Education educationMaster) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(educationMaster);
		} catch (Exception e) {
			e.printStackTrace();
			// throw e;
			return false;
		}
		return true;
	}

	@Transactional
	public Boolean updateEducation(Education educationMaster) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(educationMaster);
		} catch (Exception e) {
			e.printStackTrace();
			// throw e;
			return false;
		}
		return true;
	}

	@Transactional
	public Boolean deleteEducation(Education educationMaster) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(educationMaster);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public Education findByEducationId(Integer educationId) {
		Education educationMaster = new Education();
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Education.class);
			criteria.add(Restrictions.eq("active", "Y"));
			criteria.add(Restrictions.eq("educationId", educationId));
			educationMaster = (Education) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// throw e;
		}
		return educationMaster;
	}
}
