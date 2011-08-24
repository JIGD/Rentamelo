package com.rentamelo

class Item {	
	static searchable ={
		spellCheck "include"
	}
	String name
	String summary // vista peque�a
	String details // detalles
	long id = 0L
	Date deadLine //fecha en que acaba el anuncio (si es que acaba)
	Date dateCreated //fecha en que fue creado
	byte[] photo //foto del articulo
	String photoType
	boolean isSent = false // para saber si se envia o no 
	User user
	static hasMany = [tags : Tag]
	static fetchMode = [user:"eager"]
	
    static constraints = {
		name blank:false
		summary blank:false
		photo nullable:true, maxSize: 60000 /* 16K */
		id unique:true
		deadLine nullable:true
		details blank:false, maxSize:3000
		isSent nullable:false
		photoType(nullable:true)
		user nullable:false
    }
	
	String toString(){
		return name
		}
}
