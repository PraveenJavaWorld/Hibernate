package com.nt.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.PersonInfo;
import com.nt.utility.HibernateUtil;

public class RetrieveDateTest {

	public static void main(String[] args) {
		
		Session session = null;
		PersonInfo info = null;
		
		//get session obj
		session = HibernateUtil.getSession();
		
		try {
			//prepare entity obj
			info = session.get(PersonInfo.class, 42);
			if(info!=null) {
				System.out.println(info);
			}
			else {
				System.out.println("No Record Found");
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
