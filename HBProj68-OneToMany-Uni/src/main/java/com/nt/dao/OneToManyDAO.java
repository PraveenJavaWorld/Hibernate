package com.nt.dao;

public interface OneToManyDAO {
	
	public void saveDataUsingParent();
	public void loadDataUsingParent();
	public void addNewChildToExistingParent();
	public void deletingAParentAndItsChilds();
	public void deletingAllParentAndTheirChilds();
	public void deletingAllParentAndTheirChildsUsingQuery();
	public void deletingAllChildsOfAParent();
	public void deletingOneChildFromCollectionOfChildsOfAParent();
	public void transferOneChildOfAParentToAnotherParent();

}
