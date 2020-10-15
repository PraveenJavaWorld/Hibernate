package com.nt.test;

import com.nt.dao.TransferDataDAO;
import com.nt.dao.TransferDataDAOImpl;
import com.nt.utility.MySQLHibernateUtil;
import com.nt.utility.OracleHibernateUtil;
import com.nt.utility.PostgreSQLHibernateUtil;

public class InteractingWithThreeDBsTest {

	public static void main(String[] args) {
		
		TransferDataDAO dao = null;
		//get dao
		dao = new TransferDataDAOImpl();
		//invoke methods
		System.out.println(dao.transferMemberById(5));
		
		//close SessionFactory
		OracleHibernateUtil.closeSessionFactory();
		MySQLHibernateUtil.closeSessionFactory();
		PostgreSQLHibernateUtil.closeSessionFactory();

	}//main

}//class
