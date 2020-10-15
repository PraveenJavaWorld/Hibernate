package com.nt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.MySQLHibernateUtil;
import com.nt.utility.OracleHibernateUtil;

public class TransferDataDAOImpl implements TransferDataDAO {

	@Override
	public String transferProductById(int pid) {
		
		Session orasession = null,mysqlsession = null;
		Transaction oratx = null,mysqltx = null;
		Product prod = null;
		var idVal = 0;//java 10 variable type inference(compiler decides the datatype based on the initial value that is assigned)
		var flag = false;
		//get both Session Objects
		orasession = OracleHibernateUtil.getSession();
		mysqlsession = MySQLHibernateUtil.getSession();
		//load product from Oracle DB s/w
		oratx = orasession.beginTransaction();//dummy transaction
		prod = orasession.get(Product.class, pid);
		if(prod == null) {
			return "No Record Found";
		}
		else {
			try {
				mysqltx = mysqlsession.beginTransaction();
				idVal = (int) mysqlsession.save(prod);
				flag = true;
			} catch (HibernateException he) {
				he.printStackTrace();
				flag = false;
			}
			finally {
				if(flag) {
					mysqltx.commit();
					return "Record Copied from Oracle DB to MySQL DB having IdValue :" +idVal;
				}
				else {
					mysqltx.rollback();
					return "Record not copied from Oracle to MySQL DB";
				}
			}
		}
		
		
	}

}
