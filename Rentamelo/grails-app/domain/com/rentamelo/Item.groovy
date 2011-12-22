package com.rentamelo
import org.grails.taggable.*
import org.grails.attachmentable.*

class Item{	
static searchable = {
  only = ['name']
  //spellCheck 'include'
}
	//remover deadLine
	String name
	String details // detalles
	//Date deadLine //fecha en que acaba el anuncio (si es que acaba)
	Date dateCreated //fecha en que fue creado 
	User user
	Category category
	byte[] picture
	boolean canBeSent = false
	boolean isRented = false
	int timesRented=0
	float pricePerDay

	
	//agregar constraints
	
	
	//static fetchMode = [user:"eager"]
	
    static constraints = {
		name blank:false
		//deadLine nullable:true, min:new Date()
		details blank:false, maxSize:3000
		user nullable:false
		category nullable:false
		picture nullable:true
		pricePerDay nullable:false
    }
	
	String toString(){
		return name
		}
	
 def public static getByCategory(String categoryName){
		//Item.findAllWhere(isRented:false, category=categoryName)
	 
	 def itemList = Item.getItems()
		def categoryItemList = []
		itemList.each{
			if(it.category.name==categoryName){
				categoryItemList.add(it)
				}
			}
		categoryItemList
		}
 
 def public static getItems(){
	 Item.findAllByIsRented(false)
	 }
	
	String getByCategoryName(String categoryName){
		if(this.category.name == categoryName){
			return this
			}
		}
	
}
