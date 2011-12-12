package com.rentamelo

class Category {
	String name
    //static hasMany = [items:Item];

  
    static constraints = {
//		items(nullable:true, validator:{val, obj -> if ((val!=null)&&(obj.children!=null)) return true else false})
		name(unique:true, blank:false)
     } 
	
	String toString(){
		return name
		}
	

/*	def getItems(){
		return items.collect { it }
		}*/
	
	/*def countItems(){
		count(getItems())
		}*/
		
}
