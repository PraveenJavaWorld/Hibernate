package com.nt.test;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class JPAQBCUpdateTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		int count = 0;
		//get session obj
		session = HibernateUtil.getSession();
		
		//============= Executing Non-Select Update JPA-QBC logic =================
		try {          //SQL::UPDATE Project SET teamSize=?,location=? WHERE cost<=?;
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create CriteriaBuilder obj
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//create CriteriaQuery obj
			CriteriaUpdate<Project> ctUpdate = builder.createCriteriaUpdate(Project.class);
			//create Root obj specifying Entity class/Table name from which records should be selected
			Root<Project> root = ctUpdate.from(Project.class);// UPDATE Project
			//add root obj to CriteriaQuery obj
			ctUpdate.set(root.get("teamSize"), 12). //  SET teamSize=12	
						set(root.get("location"), "London"). //  SET location='London'
						where(builder.le(root.get("cost"), 1000000)); //WHERE cost<=1000000
			
			//create Query obj having CriteriaQuery obj
			Query query = session.createQuery(ctUpdate);
			//execute JPA-QBC Logic
			count = query.executeUpdate();
			flag = true;
		}catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Record is Updated and Number of Records Updated is :: "+count);
			}
			else {
				tx.rollback();
				System.out.println("Record is Not Updated");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}
		
		
	}//main

}//class
