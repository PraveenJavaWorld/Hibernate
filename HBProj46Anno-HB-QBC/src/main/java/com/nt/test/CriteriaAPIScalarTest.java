package com.nt.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class CriteriaAPIScalarTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		//get Session obj
		session = HibernateUtil.getSession();
		//========== Getting specific single column values using HB-QBC logic(Projections) ================  
		/*try {
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create Criteria obj
			Criteria criteria = session.createCriteria(Project.class);
			//prepare Projection obj for single column value
			Projection p1 = Projections.property("company");
			//create Criterion obj
			Criterion cond1 = Restrictions.eq("location", "Hyderabad");
			//set Projection obj to Criteria obj
			criteria.setProjection(p1);
			//add Criterion obj to Criteria obj
			criteria.add(cond1);
			//execute QBC logic
			List<String> list = criteria.list();
			list.forEach(System.out::println);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//========== Getting specific multiple column values using HB-QBC logic(Projections) ================  
		/*try {
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create Criteria obj
			Criteria criteria = session.createCriteria(Project.class);
			//prepare Projection obj for multiple column value
			Projection p1 = Projections.property("company");
			Projection p2 = Projections.property("teamSize");
			//add Projections to ProjectionList obj
			ProjectionList pList = Projections.projectionList();
			//add Projection to ProjectionList
			pList.add(p1);
			pList.add(p2);
			//create Criterion obj
			Criterion cond1 = Restrictions.eq("location", "Hyderabad");
			//set Projection obj to Criteria obj
			criteria.setProjection(p1);
			//add Criterion obj to Criteria obj
			criteria.add(cond1);
			//add ProjetionList to Criteria obj
			criteria.setProjection(pList);
			//execute QBC logic
			List<Object[]> list = criteria.list();
			list.forEach(row->{
				for(Object o:row) {
					System.out.print(o+" ");
				}
				System.out.println();
			});
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//====== Getting single aggregate result using HB-QBC logic(Projections) ================
		try {
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create Criteria obj
			Criteria criteria = session.createCriteria(Project.class);
			//prepare Projection obj for aggregate result
			Projection p1 = Projections.max("cost");//maximum value
			Projection p2 = Projections.rowCount();//aggregate value i.e Count(*)
			//add ProjetionList to Criteria obj
			criteria.setProjection(p1);
			//execute QBC logic
			List<Long> list = criteria.list();
			//process result
			System.out.println(list.get(0));
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}
		

	}

}
