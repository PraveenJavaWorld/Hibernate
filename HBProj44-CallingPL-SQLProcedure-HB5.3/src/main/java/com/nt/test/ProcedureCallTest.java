package com.nt.test;

import com.nt.dao.AuthenticationDAOImpl;
import com.nt.dao.IAuthenticationDAO;
import com.nt.utility.HibernateUtil;

public class ProcedureCallTest {

	public static void main(String[] args) {
		
		IAuthenticationDAO dao = null;
		//create DAO
		dao = new AuthenticationDAOImpl();
		//call b.method
		System.out.println(dao.authenticate("praveen", "1234"));
		//close objs
		HibernateUtil.closeSessionFactory();

	}

}
