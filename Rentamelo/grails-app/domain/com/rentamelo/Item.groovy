package com.rentamelo

class Item {
	
	long id = 0L
	Date deadLine //fecha en que acaba el anuncio (si es que acaba)
	Date dateCreated //fecha en que fue creado
	String summary // vista pequeña
	String details // detalles
	byte[] photo //foto del articulo
	boolean isSent = false // para saber si se envia o no 
	

	static hasMany = [tags : Tag]
	
    static constraints = {
		summary blank:false, unique:true
		photo nullable:true, maxSize: 16384 /* 16K */
		timeSpent min: 0L
		deadLine nullable:true
		details blank:false, maxSize:1000
		isSent nullable:false
    }
}
