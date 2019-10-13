package com.app.module.userinfo.web;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.StatusBean;
import com.app.beans.UserInfoBean;
import com.app.module.userinfo.service.IUserInfoService;

@RestController
@RequestMapping(path = "/userinfo", produces = "application/json")
public class UserInfoController {

	@Autowired
	IUserInfoService userInfoService;

	@RequestMapping(value = "/saveuserinfo",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveUserInfo(@RequestBody UserInfoBean userInfoBean) throws JsonGenerationException, JsonMappingException, IOException, JSONException {

		StatusBean statusBean = new StatusBean();
		String userinfoResponse = userInfoService.saveUserInfo(userInfoBean);
		if (userinfoResponse!=null) {
			return new ResponseEntity<String>(userinfoResponse, HttpStatus.OK);
		} else {
			JSONObject jsonResponse = new JSONObject();
			ObjectMapper mapperObj = new ObjectMapper();
			String statusStr;
			String message = "Error while processing";
			statusBean = new StatusBean(message, "400", false, "1001");
			statusStr = mapperObj.writeValueAsString(statusBean);
			jsonResponse.put("statusResponse", statusStr);
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/updateuserinfo",method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> updateuserinfo(@RequestBody UserInfoBean userInfoBean) {

		StatusBean statusBean = new StatusBean();
		statusBean = userInfoService.updateUserInfo(userInfoBean);
		if (statusBean.getStatus()) {
			return new ResponseEntity<StatusBean>(statusBean, HttpStatus.OK);
		} else {
			return new ResponseEntity<StatusBean>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/fetchuserinfo/{userinfoId}", method = RequestMethod.GET)
	public @ResponseBody String fetchUserInfo(@PathVariable(value = "userinfoId") Integer userinfoId)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		String jsonResponse = userInfoService.fetchUserInfo(userinfoId);
		return jsonResponse;
	}

	@RequestMapping(value = "/fetchAlluserInfo", method = RequestMethod.GET)
	public @ResponseBody String fetchAlluser()
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		String jsonResponse = userInfoService.fetchAllUserInfo();
		return jsonResponse;
	}

}
