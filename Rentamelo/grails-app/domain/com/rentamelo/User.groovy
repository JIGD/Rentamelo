package com.rentamelo

class User extends SecUser{
	static searchable =true
	String fullName
	String email
	String address1
	String telefono
	
	static hasMany = [items:Item]
	
    static constraints = {
    	//username blank:false, unique:true
		//password blank:false, password:true
		fullName blank:false, maxSize:50
		email email:true, blank:false, unique:true
		address1 blank:false,maxSize:100
		telefono blank:true, nullable:true, maxSize:30
	}
	
	String toString(){
		username
	}
}
