package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Address;
import com.nt.entity.StudentInfo;
import com.nt.utility.HibernateUtil;

public class ComponentMappingInsertTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		StudentInfo info1 = null,info2 = null;
		Address addrs1 = null,addrs2 = null;
		//get Session
		session = HibernateUtil.getSession();
		//prepare objects
		addrs1 = new Address();
		addrs1.setDoorNo("789-85/A");
		addrs1.setStreetName("Neekenduku Street");
		addrs1.setCity("Kakinada");
		addrs1.setState("Andhra Pradesh");
		addrs1.setPincode(533106L);
		addrs1.setCountry("INDIA");
		
		info1 = new StudentInfo();
		info1.setSname("Chandana");
		info1.setAverage(97.0f);
		info1.setAddress(addrs1);//HAS-A Relationship
		
		addrs2 = new Address();
		addrs2.setDoorNo("126-52/A");
		addrs2.setStreetName("Naakukavali Street");
		addrs2.setCity("Hyderabad");
		addrs2.setState("Telangana");
		addrs2.setPincode(500050L);
		addrs2.setCountry("INDIA");
		
		info2 = new StudentInfo();
		info2.setSname("Rocky123");
		info2.setAverage(85.2f);
		info2.setAddress(addrs2);//HAS-A Relationship
		
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//save objs
			session.save(info1);
			session.save(info2);
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object is Saved");
			} else {
				tx.rollback();
				System.out.println("Object is Not Saved");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
