package com.nt.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Department;
import com.nt.entity.EmployeeDetails;
import com.nt.utility.HibernateUtil;

public class ManyToOneDAOImpl implements ManyToOneDAO {
	
	Session session = null;
	Transaction tx = null;
	boolean flag = false;

	@Override
	public void saveDataUsingChild() {
		//get session
		session = HibernateUtil.getSession();
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//prepare objs
			//Child objs
			EmployeeDetails emp1 = new EmployeeDetails("Praveen", "RCPM", 50000);
			EmployeeDetails emp2 = new EmployeeDetails("Praveen123", "Hyderabad", 40000);
			EmployeeDetails emp3 = new EmployeeDetails("Raja", "Hyderabad", 60000);
			//parent obj
			Department dept = new Department("IT", "Hyderabad", 10);
			//set parent obj to multiple child objs
			emp1.setDept(dept);
			emp2.setDept(dept);
			emp3.setDept(dept);
			//save objs
			session.save(emp1);
			session.save(emp2);
			session.save(emp3);
			flag = true;
		}
		catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Records are inserted");
			}
			else {
				tx.rollback();
				System.out.println("Record Insertion Failed");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally

	}//saveDataUsingChild()

	@Override
	public void loadDataUsingChild() {
		
		//get session
		session = HibernateUtil.getSession();
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load objects
			Query query  = session.createQuery("from EmployeeDetails");
			List<EmployeeDetails> list = query.getResultList();
			list.forEach(emp->{
				System.out.println("Child:: "+emp.getEmpno()+" "+emp.getName()+" "+emp.getAddress()+" "+emp.getSalary());
				//get Associated Parent obj
				//Department dept = emp.getDept();
				//System.out.println("Parent:: "+dept.getDeptno()+" "+dept.getName()+" "+dept.getLocation()+" "+dept.getCapacity());
			});
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//loadDataUsingChild()

	@Override
	public void deleteAllChildsAndItsParent() {
		//get session
		session = HibernateUtil.getSession();
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load all objs
			Query query = session.createQuery("from EmployeeDetails");
			List<EmployeeDetails> list = query.getResultList();
			list.forEach(emp->{
				session.delete(emp);
			});
			
			flag = true;
		}
		catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("All Childs and Parents are Deleted");
			}
			else {
				tx.rollback();
				System.out.println("Deletion Failed");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//deleteAllChildsAndItsParent()

	@Override
	public void deleteOneChildFromParent() {
		
		//get session
		session = HibernateUtil.getSession();
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load all objs
			Query query = session.createQuery("DELETE FROM EmployeeDetails WHERE empno=:no");
			query.setParameter("no", 3);
			int count = query.executeUpdate();
			flag = true;
		}
		catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("One Child is Deleted");
			}
			else {
				tx.rollback();
				System.out.println("Deletion Failed");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//deleteOneChildFromParent()

}//class
