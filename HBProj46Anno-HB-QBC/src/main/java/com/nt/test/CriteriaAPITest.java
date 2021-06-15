package com.nt.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class CriteriaAPITest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		//get Session obj
		session = HibernateUtil.getSession();
		//========== Using QBC logic to Retrieve All Records ================  
		/*try {
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create Criteria obj
			Criteria criteria = session.createCriteria(Project.class);
			//execute QBC logic
			List<Project> list = criteria.list();
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
		
		//========= Adding Conditions(Criterion Objs) to Criteria Obj ==============
		/*try {
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create Criteria obj
			Criteria criteria = session.createCriteria(Project.class);
			//prepare Criterion objs
			Criterion cond1 = Restrictions.ge("teamSize", 5);//greater than or equal to
			Criterion cond2 = Restrictions.le("teamSize", 10);//less than or equal to
			//add Criterion objs to Critertia Obj
			criteria.add(cond1);
			criteria.add(cond2);
			//create Order Obj
			Order order = Order.desc("projName");//descending order
			criteria.addOrder(order);
			//execute QBC logic
			List<Project> list = criteria.list();
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
		
		//====== Adding multiple conditions with OR & AND clauses to Criteria obj ==============
		/*try {
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create Criteria obj
			Criteria criteria = session.createCriteria(Project.class);
			//prepare Criterion objs
			Criterion cond1 = Restrictions.between("teamSize", 5, 10);
			Criterion cond2 = Restrictions.in("company", "POLARIS","Intuit");
			Criterion cond3 = Restrictions.ilike("location", "h%"); //ilike() for case insensitive and like() for case sensitive
			//create Criterion obj having AND clause b/w cond1,cond2, OR clause with cond3
			Criterion finalCond = Restrictions.or(Restrictions.and(cond1,cond2),cond3);
			//add Criterion objs to Critertia Obj
			criteria.add(finalCond);
			//execute QBC logic
			List<Project> list = criteria.list();
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
		
		//====== Adding SQLCondition(rownum) based Criterion Obj ==================
		try {
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create Criteria obj
			Criteria criteria = session.createCriteria(Project.class);
			//prepare Criterion objs
			Criterion cond1 = Restrictions.sqlRestriction("rownum>=? and rownum<=?", 
														new Object[] {1,5}, 
														new Type[] {StandardBasicTypes.INTEGER,StandardBasicTypes.INTEGER});
			
			//add Criterion objs to Critertia Obj
			criteria.add(cond1);
			//execute QBC logic
			List<Project> list = criteria.list();
			list.forEach(System.out::println);
			
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
