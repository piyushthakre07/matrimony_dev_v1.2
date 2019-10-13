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
import org.springframework.web.servlet.ModelAndView;

import com.app.beans.StatusBean;
import com.app.beans.TemporaryBean;
import com.app.beans.UserRegistrationBean;
import com.app.beans.UserResponseBean;
import com.app.module.registration.service.IUserRegistrationService;

@RestController
@RequestMapping(path = "/registration", produces = "application/json")
public class UserRegisterController {

	@Autowired
	IUserRegistrationService userRegistrationService;

	@RequestMapping(value = "/viewuserregistration", method = RequestMethod.GET)
	public ModelAndView viewuserregistration(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("registration/viewregistration");
		return mv;
	}
	
	
	@RequestMapping(value = "/submituser", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> registerUser(HttpServletRequest request,
			@RequestBody UserRegistrationBean userRegistrationBean) {
		String message = "Error While Processing.";
		StatusBean statusBean = new StatusBean();

		statusBean = userRegistrationService.registerUser(userRegistrationBean);
		if (statusBean.getStatus()) {
			TemporaryBean temporaryBean = userRegistrationService.fetchTempInfo(userRegistrationBean.getTempId(),
					userRegistrationBean.getContactNumber());
			if (temporaryBean != null) {
				UserResponseBean userResponseBean = userRegistrationService.saveUserByApi(temporaryBean);
				if (userResponseBean != null && userResponseBean.getUserId() != null
						&& userResponseBean.getStatusResponse() != null
						&& userResponseBean.getStatusResponse().getStatus()) {
					temporaryBean.setUserId(userResponseBean.getUserId());
					UserResponseBean userInfoResponseBean = userRegistrationService.saveUserInfoByApi(temporaryBean);
					return new ResponseEntity<UserResponseBean>(userInfoResponseBean, HttpStatus.OK);
				}
				statusBean=new StatusBean(message, "400", true, "1001");
				return new ResponseEntity<StatusBean>(statusBean, HttpStatus.OK);
			}
		}
		return new ResponseEntity<StatusBean>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// otp verify- done
	// temp id temp infomation fetch- done
	// user insert with user type is USER.data fetch from temptable
	// UserInfo insert.data fetch from temptable

}
