package com.nt.dao;

import javax.persistence.ParameterMode;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;

import com.nt.utility.HibernateUtil;

/*create or replace PROCEDURE P_AUTHENTICATION 
(
  USERNAME IN VARCHAR2 , 
  PASSWORD IN VARCHAR2 ,
   RESULT OUT VARCHAR2 
) AS 
CNT Number(5);
BEGIN
  SELECT COUNT(*) INTO CNT FROM USERS_LIST WHERE USERNAME=USERNAME AND PASSWORD=PASSWORD;
  IF(CNT<>0)THEN
  RESULT:='VALID CREDENTIALS';
  ELSE
  RESULT:='INVALID CREDENTIALS';
  END IF;
END P_AUTHENTICATION;*/

public class AuthenticationDAOImpl implements IAuthenticationDAO {

	@Override
	public String authenticate(String username, String password) {
		Session session = null;
		Transaction tx = null;
		ProcedureCall call = null;
		String result = null;
		//get session obj
		session = HibernateUtil.getSession();
		//get tx or begin tx
		if(!session.getTransaction().isActive())
			tx = session.beginTransaction();
		//create ProcedureCall Obj
		call = session.createStoredProcedureCall("P_AUTHENTICATION");
		//set IN,OUT params with JDBC types and IN params with values
		call.registerParameter(1, String.class, ParameterMode.IN).bindValue(username);
		call.registerParameter(2, String.class, ParameterMode.IN).bindValue(password);
		call.registerParameter(3, String.class, ParameterMode.OUT);
		//call PL/SQL Procedure
		result = (String) call.getOutputParameterValue(3);
		
		return result;
	}

}
