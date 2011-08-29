package com.rentamelo

import grails.test.*

class CategoryIntTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	protected void testSomething() {
		def testInstances=[new Category(name:"CategoriaP").save(), new Category(name:"CategoriaH").save()]
		mockDomain(Category, testInstances)
		assertEquals(2, Category.count())
	  }
}
