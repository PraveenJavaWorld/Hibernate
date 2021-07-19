package com.nt.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Employee;
import com.nt.utility.HibernateUtil;

public class CollectionMappingInsertionTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		Employee emp = null;
		
		//get Session obj
		session = HibernateUtil.getSession();
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//prepare objs
			emp = new Employee();
			emp.setEname("Rocky");
			emp.setAddress("Hyderabad");
			emp.setFriendsList(List.of("Praveen","Vijay"));
			emp.setRelativesList(List.of("Uncle","Aunty","Sister"));
			emp.setPhoneNumbers(Set.of(987654321L,123456789L,963852741L));
			emp.setBankAccounts(Map.of("SBI",123456789L,"ICICI Bank",987654321L,"Union Bank",963852741L));
			//save objs
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
		}

	}

}
