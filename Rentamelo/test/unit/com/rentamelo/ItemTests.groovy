package com.rentamelo

import grails.test.*
import com.rentamelo.Item

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
		def item = new Item(name:"algo", summary:"Este es un item que quiero rentar", 
			details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres", 
			id:1231, photo:123, dateCreated: new Date(), deadLine: null, isSent:true )
		item.save(failOnError:true)
		assert item.get(b.id) != null
		assert item.name == "algo"
		}
}
