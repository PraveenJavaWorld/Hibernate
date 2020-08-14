package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Membership;
import com.nt.utility.HibernateUtil;

public class MergeTest1 {

	public static void main(String[] args) {
		
		Session session = null;
		Membership member = null , member1 = null , member2 = null;
		Transaction tx = null;
		boolean flag = false;
		//load session object
		session = HibernateUtil.getSession();
		//load object
		member = session.get(Membership.class, 6L);
		System.out.println(member);
		
		try {
			tx = session.beginTransaction();
			//prepare object
			member1 = new Membership();
			member1.setMid(89L);
			member1.setName("Rocky");
			member1.setAddress("Ramachandrapuram");
			member1.setRewardPoints(150L);
			//session.update(member1);  //throws  NonUniqueObjectException
			//session.save(member1);  //throws NonUniqueObjectException
			//session.delete(member1);//throws  EntityExistsException
			//session.saveOrUpdate(member1);	 //throws throws NonUniqueObjectException
			member2 = (Membership) session.merge(member1);
			System.out.println(member2);
			System.out.println(member.hashCode()+" "+member1.hashCode()+" "+member2.hashCode());
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object is Saved or Updated");
			}
			else {
				tx.rollback();
				System.out.println("Object is not Saved or Updated");
			}
			//close objects
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
