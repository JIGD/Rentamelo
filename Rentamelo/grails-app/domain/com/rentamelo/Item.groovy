package com.rentamelo

class Item {
//cambio	
	static searchable = true /*{
		mapping {spellCheck = "include"}
		}*/
	String name
	String summary // vista pequeña
	String details // detalles
	long id = 0L
	Date deadLine //fecha en que acaba el anuncio (si es que acaba)
	Date dateCreated //fecha en que fue creado
	byte[] photo //foto del articulo
	boolean isSent = false // para saber si se envia o no 
	
	static belongsTo = User
	static hasMany = [tags : Tag]
	
    static constraints = {
		name blank:false
		summary blank:false
		photo nullable:true, maxSize: 16384 /* 16K */
		id unique:true
		deadLine nullable:true
		details blank:false, maxSize:3000
		isSent nullable:false
    }
	
	String toString(){
		return name
		}
}
