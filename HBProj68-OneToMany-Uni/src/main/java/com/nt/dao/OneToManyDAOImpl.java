package com.nt.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

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
		Set<PhoneNumber> phoneSet = Set.of(ph1,ph2);
		//parent obj
		UserInfo info = new UserInfo("Rocky", "Hyderabad");
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
	public void loadDataUsingParent() {
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load Parent object
			Query query = session.createQuery("FROM com.nt.entity.UserInfo");
			List<UserInfo> list = query.getResultList();
			//list.forEach(System.out::println);
			list.forEach(user->{
				System.out.println("Parent :: "+user.getUserId()+" "+user.getUsername()+" "+user.getAddress());
				//get Associated childs of a Parent
				Set<PhoneNumber> childs = user.getPhones();
				childs.forEach(child->{
					System.out.println("Childs :: "+child);
				});
			});
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
	}//loadDataUsingParent()


	@Override
	public void addNewChildToExistingParent() {
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load Existing Parent object
			UserInfo info = session.get(UserInfo.class, 1);
			//get all existing childs of the parent
			Set<PhoneNumber> childs = info.getPhones();
			//create new Child
			PhoneNumber ph = new PhoneNumber(7777777777L, "Residence", "VI");
			//add new child to existing childs
			childs.add(ph);
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
		
	}//addNewChildToExistingParent()


	@Override
	public void deletingAParentAndItsChilds() {
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get Parent class Object
			UserInfo info = session.get(UserInfo.class, 2);
			//delete the parent
			session.delete(info);
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
				System.out.println("Records are Deleted");
			}
			else {
				tx.rollback();
				System.out.println("Records are Not Deleted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//deletingAParentAndItsChilds()


	@Override
	public void deletingAllParentAndTheirChilds() {
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load all Parent class Objects
			Query query = session.createQuery("FROM com.nt.entity.UserInfo");
			List<UserInfo> list = query.getResultList();
			list.forEach(user->{
				session.delete(user);
			});
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
				System.out.println("All Records are Deleted");
			}
			else {
				tx.rollback();
				System.out.println("All Records are Not Deleted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//deletingAllParentAndTheirChilds()


	@Override
	public void deletingAllParentAndTheirChildsUsingQuery() {
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load all Parent class Objects
			//Query query = session.createQuery("DELETE FROM com.nt.entity.UserInfo");// java.sql.SQLIntegrityConstraintViolationException: ORA-02292: integrity constraint violated - child record found
			Query query = session.createQuery("DELETE FROM com.nt.entity.UserInfo on delete cascade");//same exception
			int count = query.executeUpdate();
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
				System.out.println("All Records are Deleted");
			}
			else {
				tx.rollback();
				System.out.println("All Records are Not Deleted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//deletingAllParentAndTheirChildsUsingQuery()


	@Override
	public void deletingAllChildsOfAParent() {
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get Parent class Object
			UserInfo info = session.get(UserInfo.class, 2);
			//delete the all childs of parent
			Set<PhoneNumber> phones = info.getPhones();
			phones.removeAll(phones);
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
				System.out.println("All Childs Of A Parent are Deleted");
			}
			else {
				tx.rollback();
				System.out.println("All Childs Of A Parent are Not Deleted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//deletingAllChildsOfAParent()


	@Override
	public void deletingOneChildFromCollectionOfChildsOfAParent() {
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get Parent class Object
			UserInfo info = session.get(UserInfo.class, 2);
			//get all childs of parent
			Set<PhoneNumber> childs = info.getPhones();
			//load specific child to delete
			PhoneNumber ph = session.get(PhoneNumber.class, 34);
			childs.remove(ph);
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


	@Override
	public void transferOneChildOfAParentToAnotherParent() {
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load Source Parent object
			UserInfo parent1 = session.get(UserInfo.class, 1);
			//load all childs of the source parent
			Set<PhoneNumber> parent1Childs = parent1.getPhones();
			//get specific child you want to transfer
			PhoneNumber ph1 = session.get(PhoneNumber.class, 28);
			//Load destination Parent object
			UserInfo parent2 = session.get(UserInfo.class, 2);
			//load all childs of the destination parent
			Set<PhoneNumber> parent2Childs = parent2.getPhones();
			//delete child from the source parent and add to the destination parent(Child Transferring)
			parent2Childs.add(ph1); //No need of calling parent1Childs.remove(ph1);
			flag = true;
			session.flush();
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
				System.out.println("One Child Of A Parent is Transfered");
			}
			else {
				tx.rollback();
				System.out.println("One Child Of A Parent is Not Transfered");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//transferOneChildOfAParentToAnotherParent()

}//class
