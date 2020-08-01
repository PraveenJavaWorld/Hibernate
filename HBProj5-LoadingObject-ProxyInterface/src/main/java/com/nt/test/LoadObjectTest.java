package com.nt.test;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nt.entity.IInsurancePolicy;
import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		IInsurancePolicy policy = null;
		//load session object
		session = HibernateUtil.getSession();
		try {
			//load object
			policy = session.load(InsurancePolicy.class , 1001L);
			System.out.println(policy.getClass()+" "+policy.getClass().getSuperclass()+" "+Arrays.toString(policy.getClass().getInterfaces()));
			System.out.println(policy);
		} catch (HibernateException he) {
			System.out.println("Record not Found");
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
