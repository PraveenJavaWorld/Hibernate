package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Project proj = null;
		//get session
		session = HibernateUtil.getSession();
		try {
			//load object
			proj = session.load(Project.class, 9001L);
			System.out.println(proj);
		} catch (HibernateException he) {
			System.out.println("Record not found");
			he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
