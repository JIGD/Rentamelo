package com.rentamelo

import grails.test.*

class ItemIntTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {

    }
	

	void testCRUD(){
		def item = new Item(name:"algo", summary:"Este es un item que quiero rentar",
			details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
			id:1231, photo:123, dateCreated: new Date(), deadLine: null, isSent:true )
		item.save(failOnError:true)
		def b = Item.get(1)
		assert item.get(b.id) != null
		assert item.name == "algo"
		}
		
	void testName(){
		def item = new Item(name:"algo", summary:"Este es un item que quiero rentar",
			details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
			id:1231, photo:123, dateCreated: new Date(), deadLine: null, isSent:true )
		item.save()
		assert item.toString() == "algo"
		}
	
}
