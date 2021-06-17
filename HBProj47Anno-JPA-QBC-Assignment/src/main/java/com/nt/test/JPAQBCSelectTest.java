package com.nt.test;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class JPAQBCSelectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		//get session obj
		session = HibernateUtil.getSession();
		
		//========= Executing QBC Code with Method Chaining Concept with some Conditions ===================
		/*try {          //SQL::Select * from Project WHERE company like '___'; i.e company name is only 3 letters
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create CriteriaBuilder obj
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//create CriteriaQuery obj
			CriteriaQuery<Project> ctQuery = builder.createQuery(Project.class);
			//create Root obj 
			Root<Project> root = ctQuery.from(Project.class);
			//add root obj to CriteriaQuery obj
			ctQuery.select(root).
					where(builder.like(root.get("company"), "___"));
			
			//create Query obj having CriteriaQuery obj
			Query query = session.createQuery(ctQuery);
			//execute JPA-QBC Logic
			List<Project> list = query.getResultList();
			//process the result
			list.forEach(System.out::println);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//========= Executing QBC Code with Method Chaining Concept with some Conditions ===================
		try {          //SQL::Select * from Project WHERE teamSize>=(Select MAX(teamSize) from Project);
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create CriteriaBuilder obj
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//create CriteriaQuery obj
			CriteriaQuery<Project> ctQuery = builder.createQuery(Project.class);
			//create Root obj 
			Root<Project> root = ctQuery.from(Project.class);
			//add root obj to CriteriaQuery obj
			ctQuery.select(root);
			
			//create SubQuery obj i.e it wil create nested query in sql
			Subquery<Integer> sQuery = ctQuery.subquery(Integer.class);
			//create Root obj
			Root<Project> root1 = sQuery.from(Project.class);
			//add condition obj to SubQuery obj
			sQuery.select(builder.max(root1.get("teamSize")));
			//add root obj to CriteriaQuery obj
			ctQuery.select(root).where(builder.ge(root.get("teamSize"), sQuery));
			
			//create Query obj having CriteriaQuery obj
			Query query = session.createQuery(ctQuery);
			//execute JPA-QBC Logic
			List<Project> list = query.getResultList();
			//process the result
			list.forEach(System.out::println);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}

	}//main

}//class
