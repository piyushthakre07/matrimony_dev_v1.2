package com.app.module.master.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.CityMaster;
import com.app.module.master.dao.ICityDao;

@Repository
public class CityDaoImpl implements ICityDao {

/*	@Autowired
	SessionFactory sessionFactory;*/
	
	@Transactional
	public List<CityMaster> displayCityDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
