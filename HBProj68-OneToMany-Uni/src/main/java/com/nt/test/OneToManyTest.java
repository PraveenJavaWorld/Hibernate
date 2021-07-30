package com.nt.test;

import com.nt.dao.OneToManyDAO;
import com.nt.dao.OneToManyDAOImpl;

public class OneToManyTest {

	public static void main(String[] args) {
		
		OneToManyDAO dao = new OneToManyDAOImpl();
		//use dao
		//dao.saveDataUsingParent();
		//dao.loadDataUsingParent();
		//dao.addNewChildToExistingParent();
		//dao.deletingAParentAndItsChilds();
		//dao.deletingAllParentAndTheirChilds();
		//dao.deletingAllParentAndTheirChildsUsingQuery();
		//dao.deletingAllChildsOfAParent();
		//dao.deletingOneChildFromCollectionOfChildsOfAParent();
		dao.transferOneChildOfAParentToAnotherParent();

	}

}
