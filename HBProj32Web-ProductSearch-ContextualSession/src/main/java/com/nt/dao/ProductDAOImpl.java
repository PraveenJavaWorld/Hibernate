package com.nt.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public Product getProduct(int pid) {
		Session session = null;
		Product prod = null;
		Transaction tx = null;
		//get session object
		session = HibernateUtil.getSession();
		//dummy transaction 
		tx = session.beginTransaction();
		//get/load object
		prod = session.get(Product.class, pid);
		return prod;
	}

}
