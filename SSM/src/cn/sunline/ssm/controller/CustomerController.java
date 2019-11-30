package cn.sunline.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sunline.ssm.bean.Customer;
import cn.sunline.ssm.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/customer/toAdd")
	public String toAdd() {
		return "/customer/add";
	}
	
	@RequestMapping(value="/customer/doAdd")
	public String doAdd(Customer customer) {
		customerService.saveCustomer(customer);
		return "list";
	}

}
