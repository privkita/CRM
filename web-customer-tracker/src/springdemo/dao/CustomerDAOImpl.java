package springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Return list of customers from database
	 */
	@Override
	public List<Customer> getCustomers() {

		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query = session.createQuery("from Customer order by lastName, firstName", 
								Customer.class);
		List<Customer> customers = query.getResultList();
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get current session
		Session session = sessionFactory.getCurrentSession();
		
		// save/update the customer to database
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int id) {

		// get current session
		Session session = sessionFactory.getCurrentSession();
		
		// get Customer from session by primary key
		Customer theCustomer = session.get(Customer.class, id);
		
		return theCustomer;

	}

	@Override
	public void deleteCustomer(int id) {
		
		// get current session
		Session session = sessionFactory.getCurrentSession();
		
		// get customer by primary key
		Customer customer = getCustomer(id);
		
		// delete the customer
		session.delete(customer);
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get current session
		Session session = sessionFactory.getCurrentSession();
		
		// prepare query
		Query<Customer> query = null;
		
		if (theSearchName != null && theSearchName.trim().length() > 0) {
		 
			query = session.createQuery("from Customer WHERE lower(firstName) "
					+ "like : theName OR lower(lastName) like : theName", Customer.class);
			query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		
		} else {
			query = session.createQuery("from Customer", Customer.class);
		}
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

}
