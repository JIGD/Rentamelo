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
	
	protected void testAncestorList() {
		def father = new Category(name:"CategoriaP")
		def son= new Category(name:"CategoriaH", parent:father)
		def ancestor = new Category(name:"CategoriaAbuelo")
		def testAncestors = [father, ancestor]
		def categoryService
		def ancestors = categoryService.ancestorList()
		assertEquals([testAncestors], ancestors )
		}
}
