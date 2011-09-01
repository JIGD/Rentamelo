package com.rentamelo

import grails.test.*

class CategoryControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
	super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAncestorList() {
		def father = new Category(name:"CategoriaP")
		def son= new Category(name:"CategoriaH", parent:father)
		def ancestor = new Category(name:"CategoriaAbuelo")
		def testAncestors = [father, ancestor]
		def controller = new CategoryController()
		def ancestors = controller.ancestorList()
		
		assertEquals([testAncestors], ancestors )
		}
}
