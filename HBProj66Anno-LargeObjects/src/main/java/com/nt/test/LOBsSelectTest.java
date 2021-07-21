package com.nt.test;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.JobSeeker;
import com.nt.utility.HibernateUtil;

public class LOBsSelectTest {

	public static void main(String[] args) {
		
		byte image[] = null;
		char text[] = null;
				
		Session session = null;
		Transaction tx = null;
		FileOutputStream fos = null;
		FileWriter writer = null;
		
		//get Session obj
		session = HibernateUtil.getSession();
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//load obj
			JobSeeker seeker = session.get(JobSeeker.class,1);
			//create Destination image file having byte[] content
			fos = new FileOutputStream("store/photo.jpg");
			fos.write(seeker.getPhoto());
			
			writer = new FileWriter("store/resume.txt");
			writer.write(seeker.getResume());
			fos.flush();
			writer.flush();
			System.out.println("LOB Files are Retrieved");
			System.out.println(seeker);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
			try {
				if(fos!=null)
					fos.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			try {
				if(writer!=null)
					writer.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}

}
