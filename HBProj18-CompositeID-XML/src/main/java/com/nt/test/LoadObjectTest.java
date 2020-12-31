package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.ProgrammerProjectID;
import com.nt.entity.ProgrammerProjectInfo;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		ProgrammerProjectID id = null;
		ProgrammerProjectInfo info = null;
		//get session obj
		session = HibernateUtil.getSession();
		try {
			//begin tx
			tx = session.beginTransaction();
			//create entity objs
			id = new ProgrammerProjectID();
			id.setPid(1001);
			id.setProjId(5001);
			//load object
			info = new ProgrammerProjectInfo();
			info = session.get(ProgrammerProjectInfo.class, id);
			if(info!=null) {
				System.out.println(info);
			} else {
				System.out.println("Record Not Found");
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
