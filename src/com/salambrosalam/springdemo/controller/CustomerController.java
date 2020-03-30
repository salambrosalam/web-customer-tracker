package com.salambrosalam.springdemo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salambrosalam.springdemo.dao.CustomerDAO;
import com.salambrosalam.springdemo.entity.Customer;
import com.salambrosalam.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		// get customers from the DAO
		
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add customers to the model
		
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}  
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Customer theCustomer = new Customer();
		
		model.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute ("customer") Customer theCustomer) {
		
		
	customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam ("customerId") int theId, Model model){
	
		Customer theCustomer = customerService.getCustomer(theId);
		
		model.addAttribute("customer",theCustomer);
		
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId")int theId) {
		
		//delete the customer
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model model) {
		
		//search customers from the service
		List<Customer> theCustomers = customerService.searchCustomer(theSearchName);
		
		//add the customer model
		
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
		
	}
	
}
