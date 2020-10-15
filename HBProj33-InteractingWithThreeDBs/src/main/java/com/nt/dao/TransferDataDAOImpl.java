package com.nt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.SuperMarket_Membership;
import com.nt.utility.MySQLHibernateUtil;
import com.nt.utility.OracleHibernateUtil;
import com.nt.utility.PostgreSQLHibernateUtil;

public class TransferDataDAOImpl implements TransferDataDAO {

	@Override
	public String transferMemberById(int mid) {
		
		Session orasession = null,mysqlsession = null,postgresession=null;
		Transaction oratx = null,mysqltx = null,postgretx = null;
		SuperMarket_Membership member = null;
		var idVal = 0;//java 10 variable type inference(compiler decides the datatype based on the initial value that is assigned)
		var flag = false;
		//get both Session Objects
		orasession = OracleHibernateUtil.getSession();
		mysqlsession = MySQLHibernateUtil.getSession();
		postgresession = PostgreSQLHibernateUtil.getSession();
		//load product from Oracle DB s/w
		oratx = orasession.beginTransaction();//dummy transaction
		member = orasession.get(SuperMarket_Membership.class, mid);
		if(member == null) {
			return "No Record Found";
		}
		else {
			try {
				mysqltx = mysqlsession.beginTransaction();
				postgretx = postgresession.beginTransaction();
				idVal = (int) mysqlsession.save(member);
				idVal = (int) postgresession.save(member);
				flag = true;
			} catch (HibernateException he) {
				he.printStackTrace();
				flag = false;
			}
			finally {
				if(flag) {
					mysqltx.commit();
					postgretx.commit();
					return "Record Copied from Oracle DB to MySQL DB and PostgreSQL DB having IdValue :" +idVal;
				}
				else {
					mysqltx.rollback();
					postgretx.rollback();
					return "Record not copied from Oracle DB to MySQL DB and PostgreSQL DB";
				}
			}
		}
		
		
	}

}
