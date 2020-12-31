package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.ProgrammerProjectID;
import com.nt.entity.ProgrammerProjectInfo;
import com.nt.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		ProgrammerProjectID id = null;
		ProgrammerProjectInfo info = null;
		boolean flag = false;
		//get session obj
		session = HibernateUtil.getSession();
		try {
			//begin tx
			tx = session.beginTransaction();
			//create entity objs
			id = new ProgrammerProjectID();
			id.setPid(1001);
			id.setProjId(5001);
			
			info = new ProgrammerProjectInfo();
			info.setId(id);
			info.setPname("Praveen");
			info.setProjName("ICICI Bank Project");
			info.setDeptNo(258);
			//save object
			id = (ProgrammerProjectID) session.save(info);
			System.out.println("Generated ID Value :: "+id);
			flag = true;
		}catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		finally {
			//perform TxMgmt
			if(flag) {
				tx.commit();
				System.out.println("Record Is Saved");
			}else {
				tx.rollback();
				System.out.println("Record Is Not Saved");
			}
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
