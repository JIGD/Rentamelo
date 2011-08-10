package com.rentamelo

class Item {
	
	long id = 0L
	String name
	Date deadLine //fecha en que acaba el anuncio (si es que acaba)
	Date dateCreated //fecha en que fue creado
	String summary // vista peque�a
	String details // detalles
	byte[] photo //foto del articulo
	boolean isSent = false // para saber si se envia o no 
	

	static hasMany = [tags : Tag]
	
    static constraints = {
		name blank:false
		summary blank:false
		photo nullable:true, maxSize: 16384 /* 16K */
		id unique:true
		deadLine nullable:true
		details blank:false, maxSize:1000
		isSent nullable:false
    }
}
