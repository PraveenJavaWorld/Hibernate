package com.nt.test;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		//============= Executing Entity Query to Retrieve All Records =================
		/*try {          //SQL::Select * from Project;
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create CriteriaBuilder obj
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//create CriteriaQuery obj
			CriteriaQuery<Project> ctQuery = builder.createQuery(Project.class);
			//create Root obj specifying Entity class/Table name from which records should be selected
			Root<Project> root = ctQuery.from(Project.class);
			//add root obj to CriteriaQuery obj
			ctQuery.select(root);
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
		
		//========= Executing Entity Query with some Conditions ==========================
		/*try {          //SQL::Select * from Project WHERE projID>=? AND projID<=? ORDER BY projName desc;
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
			//create  Parameters
			ParameterExpression<Integer> param1 = builder.parameter(Integer.class);
			ParameterExpression<Integer> param2 = builder.parameter(Integer.class);
			
			//create Predicate objs representing conditions and specifying parameters
			Predicate pd1 = builder.ge(root.get("projID"), param1);//projID>=?
			Predicate pd2 = builder.le(root.get("projID"), param2);//projID<=?
			Predicate finalCond = builder.and(pd1,pd2);//projID>=? AND projID<=?
			
			//add Predicate obj to CriteriaQuery obj as where clause condition
			ctQuery.where(finalCond);//WHERE projID>=? AND projID<=?
			
			//create Order obj
			Order order = builder.desc(root.get("projName")); // projName desc
			
			//add Order obj to CriteriaQuery obj
			ctQuery.orderBy(order);//ORDER BY projName desc
			
			//create Query obj having CriteriaQuery obj
			Query query = session.createQuery(ctQuery);
			//set parameter values
			query.setParameter(param1, 1001);
			query.setParameter(param2, 1006);
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
		
		//========= Executing Above Code with Method Chaining Concept ===================
		/*try {          //SQL::Select * from Project WHERE projID>=? AND projID<=? ORDER BY projName desc;
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
					where(builder.and(builder.ge(root.get("projID"), 1001),builder.le(root.get("projID"), 1006))).
					orderBy(builder.desc(root.get("projName")));
			
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
		/*try {          //SQL::Select * from Project WHERE location in('Hyderabad','Bangalore') ORDER BY projName asc;
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
					where(root.get("location").in("Hyderabad","Bangalore")).
					orderBy(builder.asc(root.get("projName")));
			
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
		try {          //SQL::Select * from Project WHERE teamSize between(10,20) and projName like 'a%';
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
					where(builder.and(builder.between(root.get("teamSize"), 3, 20),builder.like(root.get("projName"), "A%")));
			
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
