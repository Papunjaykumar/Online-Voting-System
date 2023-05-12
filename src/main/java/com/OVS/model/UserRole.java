package com.OVS.model;

public enum UserRole {
	
	ADMIN,
	CANDIDATE,
	VOTER,
	USER;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		switch(this) {
		case ADMIN:
			return "ADMIN";
		case CANDIDATE:
			return "CANDIDATE";
		case VOTER:
			return "VOTER";
		case USER:
			return "USER";
		default:
			return "You have not assgin any role";
		}
	}
}
