package com.nt.factory;

import com.nt.component.AmazonStore;
import com.nt.component.ECommerceStore;
import com.nt.proxy.ECommerceStoreDiscountProxy;

public class ECommerceStoreFactory {
	
	public static ECommerceStore getInstance(String couponCode) {
		
		if(couponCode.equalsIgnoreCase("") || couponCode.trim().length() == 0)
			return new AmazonStore(); //real object
		else {
			if(couponCode.equalsIgnoreCase("AM10"))
				return new ECommerceStoreDiscountProxy(10);//proxy object
			else if (couponCode.equalsIgnoreCase("AM20")) 
				return new ECommerceStoreDiscountProxy(20);//proxy object
			else
				return new ECommerceStoreDiscountProxy(5);//proxy object
		}
	}//method

}//class
