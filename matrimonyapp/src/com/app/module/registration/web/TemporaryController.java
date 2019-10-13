package com.app.module.registration.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.StatusBean;
import com.app.beans.TemporaryBean;
import com.app.module.registration.service.ITemporaryService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping(path = "/registration", produces = "application/json")
public class TemporaryController {

	@Autowired
	ITemporaryService temporaryService;

	@RequestMapping(value = "/tempsave", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveTempDetails(HttpServletRequest request,
			@RequestBody TemporaryBean temporaryBean) {
		log.info("Start Class TemporaryController Method saveTempDetails ");
		StatusBean statusBean = new StatusBean();
		statusBean = temporaryService.saveTempDetails(temporaryBean);
		if (statusBean.getStatus()) {
			log.info("End Class TemporaryController Method saveTempDetails ");
			return new ResponseEntity<StatusBean>(statusBean, HttpStatus.OK);
		} else {
			log.info("End Class TemporaryController Method saveTempDetails ");
			return new ResponseEntity<StatusBean>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
