package com.nt.test;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class JPAQBCSelectScalarTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		//get session obj
		session = HibernateUtil.getSession();
		
		//============= Executing Scalar Query to Retrieve specific multiple column values =================
		/*try {          //SQL::SELECT ProjID,ProjName from Project WHERE location='Hyderabad';
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create CriteriaBuilder obj
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//create CriteriaQuery obj
			CriteriaQuery<Object[]> ctQuery = builder.createQuery(Object[].class);
			//create Root obj specifying Entity class/Table name from which records should be selected
			Root<Project> root = ctQuery.from(Project.class);//from Project
			//add root obj to CriteriaQuery obj
			ctQuery.multiselect(root.get("projID"),root.get("projName")).  // SELECT projID,projName
							where(builder.equal(root.get("location"), "Hyderabad")); //WHERE location='Hyderabad'
							
							
			//create Query obj having CriteriaQuery obj
			Query query = session.createQuery(ctQuery);
			//execute JPA-QBC Logic
			List<Object[]> list = query.getResultList();
			//process the result
			list.forEach(row->{
				for(Object o:row) {
					System.out.print(o+"  ");
				}
				System.out.println();
			});
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//============= Executing Scalar Query to Retrieve specific single column values =================
		/*try {          //SQL::SELECT ProjName from Project WHERE cost between(?,?) ORDER BY projName asc;
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create CriteriaBuilder obj
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//create CriteriaQuery obj
			CriteriaQuery<String> ctQuery = builder.createQuery(String.class);
			//create Root obj specifying Entity class/Table name from which records should be selected
			Root<Project> root = ctQuery.from(Project.class);//from Project
			//add root obj to CriteriaQuery obj
			ctQuery.multiselect(root.get("projName")).  // SELECT projName
							where(builder.between(root.get("cost"), 100000, 1000000)).//WHERE cost between(?,?)
							orderBy(builder.asc(root.get("projName"))); //ORDER BY projName asc
							
							
			//create Query obj having CriteriaQuery obj
			Query query = session.createQuery(ctQuery);
			//execute JPA-QBC Logic
			List<String> list = query.getResultList();
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
		
		//============= Executing Scalar Query for Aggregate results =================
		try {          //SQL::SELECT COUNT(*) from Project;
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create CriteriaBuilder obj
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//create CriteriaQuery obj
			CriteriaQuery<Long> ctQuery = builder.createQuery(Long.class);
			//create Root obj specifying Entity class/Table name from which records should be selected
			Root<Project> root = ctQuery.from(Project.class);//from Project
			//add root obj to CriteriaQuery obj
			ctQuery.multiselect(builder.count(root));
			// or
			//ctQuery.multiselect(builder.count(root.get("projID")));
							
			//create Query obj having CriteriaQuery obj
			Query query = session.createQuery(ctQuery);
			//execute JPA-QBC Logic
			List<Long> list = query.getResultList();
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
