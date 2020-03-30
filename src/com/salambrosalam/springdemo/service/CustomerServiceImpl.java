package com.salambrosalam.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salambrosalam.springdemo.dao.CustomerDAO;
import com.salambrosalam.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return customerDAO.getCustomers();
	}


	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		customerDAO.saveCustomer(theCustomer);
		
	}


	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomer(theId);
	}


	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDAO.deleteCustomer(theId);
		
	}


	@Override
	@Transactional
	public List<Customer> searchCustomer(String theSearchName) {
		
		return customerDAO.searchCustomers(theSearchName);
	}

}
