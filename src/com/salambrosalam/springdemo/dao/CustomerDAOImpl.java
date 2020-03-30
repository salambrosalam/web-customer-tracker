package com.salambrosalam.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.salambrosalam.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession(); 
		
		//create query
		
		org.hibernate.query.Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query and get result list
		
		List<Customer> customers = theQuery.getResultList();
		
		//return the result list
		
		
		return customers;
	}
	
	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		org.hibernate.query.Query theQuery = currentSession.createQuery("delete from Customer where id=:theCustomerId");
	
		theQuery.setParameter("theCustomerId", theId);
		
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		
	    // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        org.hibernate.query.Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
		
	}

}
