package com.rentamelo

class Category {
	String name
    static hasMany = [children:Category, items:Item];
    static belongsTo = [parent:Category];
  
    static constraints = {
		parent(nullable: true);
		children(validator:{val, obj -> if ((val.collect { it } != [] )&&(obj.collect { it } != [])){ return false} else return true})
//		items(nullable:true, validator:{val, obj -> if ((val!=null)&&(obj.children!=null)) return true else false})
		name(unique:true, blank:false)
     } 
	
	String toString(){
		return name
		}
	
	def children(){
		return children.collect { it }
		}
	def items(){
		return items.collect { it }
		}
	
	def ancestor(){
		fathers(parent)
		}
		
	def fathers(Category father){
		if (father.parent==null){
			return father			
			}
		else {
			fathers(father.parent)
			}
		}
		
}
