package com.app.module.otp.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.beans.RequestOtpVerificationBean;
import com.app.model.OtpVerification;
import com.app.module.otp.dao.IOtpDao;

@Repository
public class OtpDaoImpl implements IOtpDao {
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.module.otp.dao.IOtpDao#verifyOtpDao(com.app.beans.
	 * RequestOtpVerificationBean)
	 */
	public OtpVerification verifyOtpDao(RequestOtpVerificationBean requestBean) {
		OtpVerification otpVerification = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria crit = session.createCriteria(OtpVerification.class);
			crit.add(Restrictions.eq("contactNumber", requestBean.getContactNumber()));

			/*
			 * crit.add(Restrictions.eq("otp", requestBean.getOtp()));
			 * crit.add(Restrictions.eq("verifiedStatus", false));
			 * crit.add(Restrictions.eq("active", true));
			 * crit.add(Restrictions.eq("maxOtpAttemptStatus", false));
			 */

			// SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD
			// HH:MM:SS");
			// Date currentDate = new Date();
			// crit.add(Restrictions.ge("otpExpiringTime",
			// formatter.format(currentDate)));
			otpVerification = (OtpVerification) crit.uniqueResult();
		} catch (Exception e) {
			return null;
		}
		return otpVerification;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.module.otp.dao.IOtpDao#updateOtpVerification(com.app.model.
	 * OtpVerification)
	 */
	@Override
	public boolean updateOtpVerification(OtpVerification otpVerification) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(otpVerification);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.module.otp.dao.IOtpDao#fetchOtpVerification(java.lang.Integer)
	 */
	@Override
	public OtpVerification fetchOtpVerification(Integer otpVerificationId) {
		OtpVerification otpVerification;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria crit = session.createCriteria(OtpVerification.class);
			crit.add(Restrictions.eq("otpVerificationId", otpVerificationId));
			otpVerification = (OtpVerification) crit.uniqueResult();
		} catch (Exception e) {
			return null;
		}
		return otpVerification;
	}

}
