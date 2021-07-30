package com.nt.entity;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserInfo implements Serializable {
	
	private int userId;
	@NonNull
	private String username;
	@NonNull
	private String address;
	private Set<PhoneNumber> phones; //Special Property to build 1...* association(By holding multiple objects of child class)

}
