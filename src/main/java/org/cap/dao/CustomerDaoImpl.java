package org.cap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.cap.entities.Customer;
import org.springframework.stereotype.Repository;
@Repository
public class CustomerDaoImpl implements ICustomerDao {
	
	 private EntityManager entityManager;

	    public EntityManager getEntityManager() {
	        return entityManager;
	    }

	    @PersistenceContext
	    public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }
	@Override
	public Customer findById(int cid) {
		 Customer customer = entityManager.find(Customer.class, cid);
	        return customer;
	}

	@Override
	public Customer register(Customer customer) {
		  getEntityManager().persist(customer);
		  return customer;
	}

	@Override
	public Customer update(Customer customer) {
		 customer = entityManager.merge(customer);
		 return customer;
	}
	@Override
	public Boolean delete(int cid) {
		 Customer customer = entityManager.find(Customer.class, cid);
		getEntityManager().remove(customer);
		return true;
	}

	@Override
	public List<Customer> fetchAll() {
		 String jql = "from Customer";
	        TypedQuery<Customer> query = entityManager.createQuery(jql, Customer.class);
	        List<Customer> store = query.getResultList();
		return store;
	}

}
