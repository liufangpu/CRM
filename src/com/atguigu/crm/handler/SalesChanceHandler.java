package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {
	@Autowired 
	private SalesChanceService salesChanceService;
	@RequestMapping(value="/dispatch",method=RequestMethod.GET )
	public String dispatch(@RequestParam(value="id") String id ,Map<String,Object>map) {
		SalesChance chance = salesChanceService.getById(Integer.parseInt(id));
		
		map.put("chance", chance);
		
		return "/chance/dispatch";
	}
	
	
	@RequestMapping(value="/list2")
	public String list2(@RequestParam(value="pageNo",required=false) String pageNoStr,Map<String,Object>map,HttpServletRequest request) throws ParseException{
		Map<String, Object> params = WebUtils.getParametersStartingWith(request,"search_");
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);
		int pageNo=1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			
		}
		
		Page<SalesChance> page=salesChanceService.getPage2(pageNo,params);
		
		map.put("page", page);
		System.out.println("不去你那里了。。。");
		
		return "/chance/list";
	}
	
	
	
	private String parseRequestParams2QueryString(Map<String, Object> params) {
StringBuilder result = new StringBuilder("");
		
		for(Map.Entry<String, Object> entry: params.entrySet()){
			String key = entry.getKey();
			Object val = entry.getValue();
			
			result.append("search_")
			      .append(key)
			      .append("=")
			      .append(val)
			      .append("&");
		}
		
		return result.toString();
		
	}



	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(SalesChance chance,RedirectAttributes attributes){
		salesChanceService.update(chance);
		
		attributes.addFlashAttribute("message", "修改成功");
		
		return"redirect:/chance/list";
		
	}

	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String,Object>map){
		map.put("chance", salesChanceService.getById(id));
		
		return "/chance/input";
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam(value="id") String idStr,RedirectAttributes attributes) {
		int id=-1;
		
		try {
			id=Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
		}
		
		try {
			salesChanceService.delete(id);
		} catch (Exception e) {
			attributes.addFlashAttribute("message", "此记录有关联不能删除！");
			return"redirect:/chance/list";
			
		}
		attributes.addFlashAttribute("message","删除成功");
		return"redirect:/chance/list";
	}
	
	@RequestMapping(value="/" ,method=RequestMethod.POST)
	public String save(SalesChance chance,RedirectAttributes attributes){
		salesChanceService.save(chance);
		attributes.addFlashAttribute("message","添加成功");
		
		
		return"redirect:/chance/list";
	}
	
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String,Object>map){
		map.put("chance", new SalesChance());
		
		return "chance/input";
	}
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNostr, 
			Map<String,Object>map){
		     int pageNo=1;
		   
			if (pageNostr!=null) {
				pageNo=Integer.parseInt(pageNostr);
			}
		
		
		
		
		Page<SalesChance> page=salesChanceService.getPage(pageNo);
		System.out.println("page 对象："+page);
		map.put("page", page);
		System.out.println("到这里来了。。。。");
		return "chance/list";
	}
}
