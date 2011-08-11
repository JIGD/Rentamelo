package com.rentamelo

class User {
	
	String username
	String password
	String fullName
	String address1
	String address2
	
	static hasMany = [items:Item]
	
    static constraints = {
    	username blank:false, unique:true
		password blank:false, password:true
		fullName blank:false
		address1 blank:false
		address2 nullable:true		
	}
	
	String toString(){
		username
	}
}
