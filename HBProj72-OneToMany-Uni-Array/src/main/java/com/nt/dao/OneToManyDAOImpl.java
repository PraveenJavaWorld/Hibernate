package com.nt.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.PhoneNumber;
import com.nt.entity.UserInfo;
import com.nt.utility.HibernateUtil;

public class OneToManyDAOImpl implements OneToManyDAO {
	
	Session session = null;
	Transaction tx = null;
	boolean flag = false;
	

	@Override
	public void saveDataUsingParent() {
		
		//get Session
		session = HibernateUtil.getSession();
		//prepare objs
		//child objs
		PhoneNumber ph1 = new PhoneNumber(9999999999L, "Personal", "JIO");
		PhoneNumber ph2 = new PhoneNumber(8888888888L, "Office", "AIRTEL");
		PhoneNumber[] phoneSet = new PhoneNumber[] {ph1,ph2};
		//parent obj
		UserInfo info = new UserInfo("Praveen", "RCPM");
		info.setPhones(phoneSet);
		
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//save objs
			session.save(info);
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

	}//saveDataUsingParent()

	
	@Override
	public void deletingOneChildFromCollectionOfChildsOfAParent() {
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get Parent class Object
			UserInfo info = session.get(UserInfo.class, 1);
			//get all childs of parent
			PhoneNumber[] childs = info.getPhones();
			//delete record
			childs[1] = null;
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
				System.out.println("One Child Of A Parent are Deleted");
			}
			else {
				tx.rollback();
				System.out.println("One Child Of A Parent are Not Deleted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//deletingOneChildFromCollectionOfChildsOfAParent()


}//class
