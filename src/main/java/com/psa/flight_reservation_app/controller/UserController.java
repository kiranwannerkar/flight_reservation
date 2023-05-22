package com.psa.flight_reservation_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psa.flight_reservation_app.entity.User;
import com.psa.flight_reservation_app.repositories.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/showLoginPage")
	public String showLoginPage() {
		return "login/login";
		
	}
	@RequestMapping("showRegistration")
	public String showRegistration() {
		return "login/showRegistration"; // login/showRegistration this path u have to follow bcz it does not fall under
											// jsps
	}

	@RequestMapping("/saveRegistration")
	public String saveRegistration(@ModelAttribute("user") User user) { // with help of @ModelAttribute we will read data from FORM
		// all data from jsp page should get store inside object User and this obj push to the database
		userRepository.save(user);
		return "login/login"; // once register data then redirect to login page
	}
	
	@RequestMapping("/verifyLogin")
	public String verifyLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password, ModelMap modelMap) {// to fetch parameter we use R
		User user = userRepository.findByEmail(emailId);//if no record found then then it will give u null pointer exc..
		if(user!=null) {
		
		if(user.getEmail().equals(emailId) && user.getPassword().equals(password)){
			return "findFlights";
		}else {
			modelMap.addAttribute("error","invalid username/password");
			return "login/login";// when u return login page i would prefer error message
		}
		
		}else {
			modelMap.addAttribute("error","invalid username/password");
			return "login/login";// when u return login page i would prefer error message
		}
		
	}

}
