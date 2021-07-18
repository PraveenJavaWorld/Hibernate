package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Employee;
import com.nt.entity.Person;
import com.nt.entity.Student;
import com.nt.utility.HibernateUtil;

public class InheritanceMappingInsertTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		Person per = null;
		Student stud = null;
		Employee emp = null;
		
		//get Session obj
		session = HibernateUtil.getSession();
		//prepare objs
		per = new Person();
		per.setName("Praveen");
		per.setAddress("RCPM");
		
		stud = new Student();
		stud.setName("Rocky");
		stud.setAddress("Hyderabad");
		stud.setAverage(97.03F);
		stud.setBranch("MCA");
		stud.setCollege("DLR PG College");
		
		emp = new Employee();
		emp.setName("Praveen CH");
		emp.setAddress("Ramachandrapuram");
		emp.setDesignation("Java Developer");
		emp.setSalary(40000.f);
		emp.setDeptNo(123);
		
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			session.save(per);
			session.save(stud);
			session.save(emp);
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Records are Inserted");
			}
			else {
				tx.rollback();
				System.out.println("Records are Not Inserted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
