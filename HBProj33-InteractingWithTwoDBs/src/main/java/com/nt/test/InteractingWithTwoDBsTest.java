package com.nt.test;

import com.nt.dao.TransferDataDAO;
import com.nt.dao.TransferDataDAOImpl;
import com.nt.utility.MySQLHibernateUtil;
import com.nt.utility.OracleHibernateUtil;

public class InteractingWithTwoDBsTest {

	public static void main(String[] args) {
		
		TransferDataDAO dao = null;
		//get dao
		dao = new TransferDataDAOImpl();
		//invoke methods
		System.out.println(dao.transferProductById(456));
		
		//close SessionFactory
		OracleHibernateUtil.closeSessionFactory();
		MySQLHibernateUtil.closeSessionFactory();

	}//main

}//class
