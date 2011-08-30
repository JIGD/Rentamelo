package com.rentamelo

class Category {
	String name
    static hasMany = [children:Category, items:Item];
    static belongsTo = [parent:Category];
  
    static constraints = {
		parent(nullable: true);
		children(nullable: true, validator:{return (items!=null)});
		items(nullable:true, validator:{return (children!=null)})
		name(unique:true, blank:false)
     } 
	
	String toString(){
		return name
		}
}
