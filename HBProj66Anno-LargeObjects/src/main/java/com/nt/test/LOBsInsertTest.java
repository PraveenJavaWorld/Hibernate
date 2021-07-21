package com.nt.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.JobSeeker;
import com.nt.utility.HibernateUtil;

public class LOBsInsertTest {

	public static void main(String[] args) {
		
		byte image[] = null;
		char text[] = null;
		try(FileInputStream fis = new FileInputStream("Log4J.jpg")){
			//prepare byte[] from image file
			image = new byte[fis.available()];
			fis.read(image);
			//prepare char[] from text file
			File file = new File("Abstract.docx");
			try(FileReader reader = new FileReader(file)){
				text = new char[(int) file.length()];
				reader.read(text);
			}//try2
 		
		}//try 1
		catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		JobSeeker seeker = null;
		
		//get Session obj
		session = HibernateUtil.getSession();
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//prepare objs
			seeker = new JobSeeker();
			seeker.setName("Praveen");
			seeker.setAddress("RCPM");
			seeker.setPhoto(image);
			seeker.setResume(text);
			int idVal = (int) session.save(seeker);
			System.out.println("Generated ID Value :: "+idVal);
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
				System.out.println("Record is Inserted");
			}
			else {
				tx.rollback();
				System.out.println("Record is Not Inserted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}

	}

}
