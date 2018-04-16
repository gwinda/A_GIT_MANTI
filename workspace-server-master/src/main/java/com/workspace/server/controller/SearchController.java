package com.workspace.server.controller;

import com.workspace.server.model.AjaxResponseBody;
import com.workspace.server.model.User;
import com.workspace.server.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {

	
	@PostMapping("/api/search")
	public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors){
		AjaxResponseBody result = new AjaxResponseBody();
		
//		if(errors.hasErrors()) {
//			result.setMsg(errors.getAllErrors()
//					.stream().map(x -> x.getDefaultMessage())
//					.collect(Collectors.joining(",")));
//			return ResponseEntity.badRequest().body(result);
//		}
		
//		List<User> users = userService.findByUserNameOrEmail(search.getUsername());
		if(1==0) {
			result.setMsg("no user found!");
		}else {
			result.setMsg("success");
		}

		User user = new User();
		user.setUsername("123");
		result.setResult(user);
		
		return ResponseEntity.ok(result);
	}

}
