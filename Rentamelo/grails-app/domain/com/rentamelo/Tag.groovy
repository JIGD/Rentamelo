package com.rentamelo

class Tag {
	String name
	
	static belongsTo = Item
	
    static constraints = {
    	name  blank:false, unique:true
	}
	
	String toString(){
		return name
		}
}