package com.app.module.education.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beans.EducationBean;
import com.app.beans.StatusBean;
import com.app.module.education.service.IEducationService;

@Controller
@RequestMapping(path = "/education", produces = "application/json")
public class EducationController {

	@Autowired
	IEducationService educationService;

	@RequestMapping(value = { "/showEducation" }, method = RequestMethod.GET)
	  public String driverMaster() {
	      return "education/educationMaster";
	  }
	
	@RequestMapping(value = "/insertEducation", method = RequestMethod.POST)
	public ResponseEntity<?> insertEducation(HttpServletRequest request, @RequestBody EducationBean educationBean) {
		String message = "";
		StatusBean statusBean=new StatusBean();
		try {
			Boolean status = educationService.insertEducation(educationBean);
			if (status) {
				message = "Success";
				return new ResponseEntity<StatusBean>(statusBean, HttpStatus.OK);
				
			} else {
				message = "Fail";
			}
			return new ResponseEntity<String>(message, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/updateEducation", method = RequestMethod.POST)
	public ResponseEntity<String> updateEducation(HttpServletRequest request, @RequestBody EducationBean educationBean) {
		String message = "";
		try {
			Boolean status = educationService.updateEducation(educationBean);
			if (status) {
				message = "Successfully updated";
			} else {
				message = "Failed to update";
			}
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/deleteEducation", method = RequestMethod.POST)
	public ResponseEntity<String> deleteEducation(HttpServletRequest request, @RequestBody EducationBean education) {
		String message = "";
		try {
			Boolean status = educationService.deleteEducation(education.getEducationId());
			if (status) {
				message = "Successfully deleted";
			} else {
				message = "Failed to delete";
			}
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error", HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/findByEducationId", method = RequestMethod.POST)
	public ResponseEntity<?> findByEducationId(HttpServletRequest request, @RequestBody EducationBean education) {
		EducationBean educationBean = null;
		StatusBean statusBean=new StatusBean();
		try {
			educationBean = educationService.findByEducationId(education.getEducationId());
			if (educationBean != null) {
				statusBean.setMessage("Success");
				statusBean.setSatusCode("501");
				statusBean.setStatus(true);
				return new ResponseEntity<EducationBean>(educationBean, HttpStatus.OK);
			} else {
				statusBean.setMessage("No Record Found");
				statusBean.setSatusCode("501");
				statusBean.setStatus(true);
				return new ResponseEntity<StatusBean>(statusBean, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			statusBean.setMessage("Error While Processing");
			statusBean.setSatusCode("404");
			statusBean.setStatus(false);
			return new ResponseEntity<StatusBean>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
