package com.atguigu.crm.handler;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerDrainService;
@RequestMapping("/drain")
@Controller
public class CustomerDrainHandler {
	@Autowired
	private CustomerDrainService customerDrainService;
	
	@RequestMapping(value="/list")
	public String list(@RequestParam(value="pageNo",required=false)String pageNoStr, Map<String,Object>map){
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		
		Page<CustomerDrain>page=customerDrainService.getPage(pageNo);
		System.out.println("page"+page);
		map.put("page", page);
		return "drain/list";
	}
	
	@RequestMapping(value="/toConfirm")
	public String toConfirm(@RequestParam("id") Integer id,Map<String,Object>map){
		CustomerDrain drain=customerDrainService.getById(id);
		
		map.put("drain", drain);
		
		return "drain/confirm";
	}
	
	@RequestMapping("/confirm/{id}")
	public String confirm(CustomerDrain customerDrain,RedirectAttributes attributes){
		Date date = new Date();
		customerDrain.setDrainDate(date);
		
		customerDrainService.confirm(customerDrain);
		attributes.addFlashAttribute("message", "操作成功");
		return "redirect:/drain/list";
	}
	
	@RequestMapping(value="/toDelay/{id}",method=RequestMethod.GET)
	public String delay(@PathVariable("id")Integer id,Map<String,Object>map){
		CustomerDrain drain=customerDrainService.getById(id);
		map.put("drain", drain);
		return "drain/delay";
	}
	
	@RequestMapping(value="/delay",method=RequestMethod.POST)
	public String delay(@RequestParam("id")Integer id,CustomerDrain drain,RedirectAttributes attributes){
		
		customerDrainService.delay(drain,id);
		return "redirect:/drain/list";
	}
	
	
	
	
	
	
}
