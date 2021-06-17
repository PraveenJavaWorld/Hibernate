package com.nt.test;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class JPAQBCDeleteTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		int count = 0;
		//get session obj
		session = HibernateUtil.getSession();
		
		//============= Executing Non-Select Delete JPA-QBC logic =================
		try {          //SQL::DELETE from Project WHERE cost between(?,?);
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create CriteriaBuilder obj
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//create CriteriaQuery obj
			CriteriaDelete<Project> ctDelete = builder.createCriteriaDelete(Project.class);
			//create Root obj specifying Entity class/Table name from which records should be selected
			Root<Project> root = ctDelete.from(Project.class);// DELETE from Project
			//add root obj to CriteriaDelete obj
			ctDelete.where(builder.between(root.get("cost"), 10000, 500000));// WHERE cost between(10000, 500000)
		
							
			//create Query obj having CriteriaQuery obj
			Query query = session.createQuery(ctDelete);
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
