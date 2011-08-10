package com.rentamelo

import grails.test.*

class ItemTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {

    }
	
	void testDatabase(){
		}
	
	void testCRUD(){
		def i = new Item(name:"algo", summary:"Este es un item que quiero rentar", 
			details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres", 
			id:1231, photo:123, dateCreated: new Date(), deadLine: null, isSent:true )
		i.save()
		assert Item.get(b.id) != null
		assert i.name == "algo"
		}
}
