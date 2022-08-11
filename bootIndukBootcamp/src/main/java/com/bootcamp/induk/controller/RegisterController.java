package com.bootcamp.induk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import com.bootcamp.induk.domain.UserDto;
import com.bootcamp.induk.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootcamp.induk.utils.UserValidator;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
	
	final private UserService userService;
	
	final int FAIL = 0;
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.setValidator(new UserValidator());	// UserValidator를 WebDataBinder의 로컬 validator로 등록
	}
	
	@GetMapping("/add")
	public String regiter() {
		return "registerForm";
	}
	
	@PostMapping("/add")
	public String save(@Valid UserDto userDto, BindingResult result, Model m) throws Exception {
		
		// UserDto 객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 함
		if(!result.hasErrors()) {
			// DB에 신규회원 정보 저장
			int rowCnt = userService.createUser(userDto);
			
			if(rowCnt != FAIL)
				return "loginForm";
		}		
		return "registerForm";
	}
}
