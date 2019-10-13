package com.app.module.education.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.EducationBean;
import com.app.beans.UserTracker;
import com.app.model.CourceMaster;
import com.app.model.Education;
import com.app.model.EducationLevelMaster;
import com.app.model.SepciallazationMaster;
import com.app.model.UniversityMaster;
import com.app.model.User;
import com.app.module.education.dao.IEducationDao;
import com.app.module.education.service.IEducationService;

@Service
public class EducationServiceImpl implements IEducationService {

	@Autowired
	IEducationDao educationDao;

	@Autowired
	UserTracker userTracker;

	public Boolean insertEducation(EducationBean educationBean) {
		try {
			Education educationMaster = new Education();
			BeanUtils.copyProperties(educationMaster, educationBean);
			EducationLevelMaster educationLevelMaster = new EducationLevelMaster();
			educationLevelMaster.setEduLevelId(educationBean.getEduLevelId());
			CourceMaster courceMaster = new CourceMaster();
			courceMaster.setCourceId(educationBean.getCourceId());
			SepciallazationMaster sepciallazationMaster = new SepciallazationMaster();
			sepciallazationMaster.setSepciallazationId(educationBean.getSepciallazationId());
			UniversityMaster universityMaster = new UniversityMaster();
			universityMaster.setUniversityId(universityMaster.getUniversityId());
			User user = new User();
			user.setUserId(userTracker.getUserId());
			educationDao.insertEducation(educationMaster);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Boolean updateEducation(EducationBean educationBean) {
		try {
			Education educationMaster = new Education();
			BeanUtils.copyProperties(educationMaster, educationBean);
			educationMaster.setEducationId(educationBean.getEducationId());
			EducationLevelMaster educationLevelMaster = new EducationLevelMaster(educationBean.getEducationId());
			educationMaster.setEducationLevelMaster(educationLevelMaster);
			CourceMaster courceMaster = new CourceMaster(educationBean.getCourceId());
			educationMaster.setCourceMaster(courceMaster);
			SepciallazationMaster sepciallazationMaster = new SepciallazationMaster(
					educationBean.getSepciallazationId());
			educationMaster.setSepciallazationMaster(sepciallazationMaster);
			UniversityMaster university = new UniversityMaster(educationBean.getUniversityId());
			educationMaster.setUniversity(university);
			educationDao.updateEducation(educationMaster);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Boolean deleteEducation(Integer educationId) {

		try {
			Education educationMaster = new Education();
			educationMaster.setEducationId(educationId);
			educationDao.deleteEducation(educationMaster);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public EducationBean findByEducationId(Integer educationId) {

		EducationBean educationBean = new EducationBean();
		try {
			Education educationMaster = educationDao.findByEducationId(educationId);
			if (educationMaster != null) {
				BeanUtils.copyProperties(educationBean, educationMaster);
				educationBean.setEduLevelId(educationMaster.getEducationLevelMaster().getEduLevelId());
				educationBean.setCourceId(educationMaster.getCourceMaster().getCourceId());
				educationBean.setSepciallazationId(educationMaster.getSepciallazationMaster().getSepciallazationId());
				educationBean.setUniversityId(educationMaster.getUniversity().getUniversityId());
				educationBean.setUserId(educationMaster.getUser().getUserId());
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		return educationBean;
	}

}
