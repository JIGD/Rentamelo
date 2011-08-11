package com.rentamelo

class User {
	
	String username
	String password
	String fullName
	String email
	String address1
	String address2
	byte[] avatar
	
	static hasMany = [items:Item]
	
    static constraints = {
    	username blank:false, unique:true
		password blank:false, password:true
		fullName blank:false
		email blank:false, unique:true
		address1 blank:false
		address2 nullable:true
		avatar nullable:true, maxSize:32768	//32k	
	}
	
	String toString(){
		username
	}
}
