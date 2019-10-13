package com.app.module.master.dao;

import java.util.List;

import com.app.model.CountryMaster;

public interface ICountryDao {

	public List<CountryMaster> displayCountryDao();

	Boolean insertCountry(CountryMaster countryMaster);
	
	public Boolean updateCountry(CountryMaster countryMaster);
	
	public Boolean deleteCountry(CountryMaster countryMaster);
	
	public CountryMaster findByCountryId(Integer countryId);
}
