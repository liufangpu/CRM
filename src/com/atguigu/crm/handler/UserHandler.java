package com.atguigu.crm.handler;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.service.UserService;

@RequestMapping("/user")
@Controller
public class UserHandler {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password,
			HttpSession session,
			RedirectAttributes attributes, 
			Locale locale){
		User user = userService.login(username, password);
		
		if(user != null){
			session.setAttribute("user", user);
			System.out.println(user.getRole());
			return "redirect:/success";
		}
		
		String code = "error.user.login";
		Object [] args = null;
		
		String message = messageSource.getMessage(code, args, locale);
		attributes.addFlashAttribute("message", message);
		attributes.addFlashAttribute("username", username);
		return "redirect:/index";
	}
	
}
