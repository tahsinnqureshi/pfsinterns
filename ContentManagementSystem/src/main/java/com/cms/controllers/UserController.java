package com.cms.controllers;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cms.entity.AddContent;
import com.cms.entity.AppUser;
import com.cms.repository.UserRepository;
import com.cms.service.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServices userServices;
	
	 @GetMapping("/register")
	    public String register(Model model) {
		 model.addAttribute("user", new AppUser());
	        return "register";
	    }
	 @GetMapping("/admin-user")
	    public String viewAdminPage(HttpSession session, Model model) {
		 AppUser storedUser = (AppUser) session.getAttribute("user");
		   model.addAttribute("user", storedUser);
		return "welcomepage";
		   
	 }
	 
	 @GetMapping("/login")
	    public String login(Model model) {
	        model.addAttribute("user", new AppUser());
	        return "login";
	    }
	 @PostMapping("/loginPage")
	 public String loginPage(@ModelAttribute("user") AppUser appUser, HttpSession session, Model model) {
	     // Find user by email
	     AppUser storedUser = userRepository.findByEmail(appUser.getEmail())
	                           .orElse(null);  // Ensure this returns an Optional

	     if (storedUser != null && passwordEncoder.matches(appUser.getPassword(), storedUser.getPassword())) {
	         session.setAttribute("user", storedUser); // Store user in session
	         return "redirect:/admin-user";  // Redirect to admin-user page after successful login
	     } else {
	         model.addAttribute("pnmsg", "Email or Password incorrect....!");
	         return "login";  // Return to login page with error message
	     }
	 }




	 
	 
	 @PostMapping("/save-user")
	 public String handleSubmitbtn(@ModelAttribute("user") AppUser appUser, Model model) {
	     // Check if the email already exists
	     boolean emailExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
	     
	     if (emailExists) {
	         model.addAttribute("pnmsg", "Email address is already used. Try again.");
	         return "register";  // Return to registration page with error message
	     } else {
	         // Save new user
	         String msg = userServices.saveUser(appUser);
	         model.addAttribute("msg", msg);
	         model.addAttribute("user", new AppUser());  // Clear form after submission
	         return "register";  // Return to registration page
	     }
	 }
	 
	     @GetMapping("/logout")
	     public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	         request.getSession().invalidate();
	         response.sendRedirect("/");
	         return "viewContent";  // Ensure correct redirection after logout
	     }
	 }


	

