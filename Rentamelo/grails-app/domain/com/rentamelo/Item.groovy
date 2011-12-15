package com.rentamelo
import org.grails.taggable.*
import org.grails.attachmentable.*

class Item{	
	static searchable ={
		spellCheck "include"
	}
	static attachmentable = true
	
	String name
	String summary // vista pequeña
	String details // detalles
	Date deadLine //fecha en que acaba el anuncio (si es que acaba)
	Date dateCreated //fecha en que fue creado 
	User user
	Category category
	byte[] picture
	
	//static fetchMode = [user:"eager"]
	
    static constraints = {
		name blank:false
		summary blank:false
		deadLine nullable:true, min:new Date()
		details blank:false, maxSize:3000
		user nullable:false
		category nullable:true
		picture nullable:true
    }
	
	String toString(){
		return name
		}
	
 def getByCategory(String categoryName){
		Item.findAllByCategory(categoryName)
		}
	
	String getByCategoryName(String categoryName){
		if(this.category.name == categoryName){
			return this
			}
		}
	
	transient def beforeDelete = {
		withNewSession{
			removeAttachments()
		}
	}
	
	def onAddAttachment = {
		hasPhoto=true
		}
}
