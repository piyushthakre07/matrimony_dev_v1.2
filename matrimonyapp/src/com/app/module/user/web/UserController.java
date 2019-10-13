package com.app.module.user.web;

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
import com.app.beans.UserBean;
import com.app.module.user.service.IUserService;

@RestController
@RequestMapping(path = "/user",consumes="application/json", produces = "application/json")
public class UserController {

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/saveuser",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveUser(@RequestBody UserBean userBean) throws JsonGenerationException, JsonMappingException, IOException, JSONException {

		String response = userService.saveUser(userBean);
		if (response!=null) {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} else {
			JSONObject jsonResponse = new JSONObject();
			ObjectMapper mapperObj = new ObjectMapper();
			String statusStr;
			String message = "Error while processing";
			StatusBean statusBean = new StatusBean(message, "400", false, "1001");
			statusStr = mapperObj.writeValueAsString(statusBean);
			jsonResponse.put("statusResponse", statusStr);
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/updateuser",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> updateuser(@RequestBody UserBean userBean) {

		StatusBean statusBean = new StatusBean();
		statusBean = userService.updateUser(userBean);
		if (statusBean.getStatus()) {
			return new ResponseEntity<StatusBean>(statusBean, HttpStatus.OK);
		} else {
			return new ResponseEntity<StatusBean>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/fetchuser/{userId}", method = RequestMethod.GET)
	public @ResponseBody String fetchUser(@PathVariable(value = "userId") Integer userId)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		String jsonResponse = userService.fetchUser(userId);
		return jsonResponse;
	}

	@RequestMapping(value = "/fetchAlluser", method = RequestMethod.GET)
	public @ResponseBody String fetchAlluser()
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		String jsonResponse = userService.fetchAllUser();
		return jsonResponse;
	}

}
