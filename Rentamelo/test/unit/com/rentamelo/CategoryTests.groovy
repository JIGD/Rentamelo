package com.rentamelo

import grails.test.*

class CategoryTests extends GrailsUnitTestCase {


	public void xtestMocks() {
		def testInstances=[new Category(name:"CategoriaP"), new Category(name:"CategoriaH")]
		mockDomain(Category, testInstances)
		assertEquals(2,Category.count())
	  }
	
	public void testValidations(){
		def testInstances=[new Category(name:"CategoriaP"), new Category(name:"CategoriaA")]
		mockDomain(Category, testInstances)
		def category = new Category(name:"CategoriaA")
		assertFalse category.validate()
		def categoryS = new Category(name:"Hijo")
		def categoryF = new Category(name:"Padre", children:categoryS)
		assertTrue categoryF.validate()
		def user1 = new User(fullName:"admon", email:"admon@algo.com", address1:"this")
		def item = new Item(name:"algo", summary:"Este es un item que quiero rentar",
			details:"detalles",	dateCreated: new Date(), deadLine: new Date(), isSent:true, user:user1)
		categoryF = new Category(name:"Padre", children:categoryS, items:item)
		assertFalse categoryF.validate()
		}
	


}
