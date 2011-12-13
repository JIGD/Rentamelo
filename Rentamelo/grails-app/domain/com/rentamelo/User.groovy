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
		fullName blank:false
		email nullable:false, unique:true
		address1 blank:false
		telefono blank:true, nullable:true
	}
	
	String toString(){
		username
	}
}
