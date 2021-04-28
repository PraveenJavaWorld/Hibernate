package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.PersonInfo;
import com.nt.utility.HibernateUtil;

public class InsertDateTest {

	public static void main(String[] args) {
		
		Session session = null;
		PersonInfo info = null;
		Transaction tx = null;
		int idVal = 0;
		boolean flag = false;
		//get session obj
		session = HibernateUtil.getSession();
		//prepare entity obj
		info = new PersonInfo();
		info.setPname("Praveen");
		info.setAddress("Ramachandrapuram");
		info.setDob(new java.util.Date(97,11 , 18, 06, 30, 30));
		info.setDoj(new java.util.Date());
		info.setDom(new java.util.Date(87, 8, 17));
		try {
			//begin tx
			tx = session.beginTransaction();
			idVal = (int) session.save(info);
			System.out.println("Generated ID Value :: "+idVal);
			flag = true;
			
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Record Is Saved");
			}
			else {
				tx.rollback();
				System.out.println("Record Is Not Saved");
			}
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
