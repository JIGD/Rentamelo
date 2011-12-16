package com.rentamelo

import grails.test.*

class CategoryTests extends GrailsUnitTestCase {


	public void xtestMocks() {
		def testInstances=[new Category(name:"CategoriaP"), new Category(name:"CategoriaH")]
		mockDomain(Category, testInstances)
		assertEquals(2,Category.count())
	  }
	
	public void testCategoryCollections(){ 
		def category = new Category(name:"CategoriaH")
		def category2 = new Category(name:"CategoriaH2")
		def categories = [category, category2]
		def categoryF = new Category(name:"CategoriaF", children: categories)
		assertEquals(categories, categoryF.children())
		def categoryG = new Category(name:"CategoriaG")	
		}
	
	public void xtestValidations(){
		def testInstances=[new Category(name:"CategoriaP"), new Category(name:"CategoriaA")]
		mockDomain(Category, testInstances)
		def category = new Category(name:"CategoriaA")
		assertFalse category.validate()
		def categoryS = new Category(name:"Hijo")
		def sons = [category, categoryS]
		def categoryF = new Category(name:"Padre", children:sons)		
		assertTrue categoryF.validate()
		def user1 = new User(fullName:"admon", email:"admon@algo.com", address1:"this")
		def item = new Item(name:"algo", summary:"Este es un item que quiero rentar",
			details:"detalles",	dateCreated: new Date(), isSent:true, user:user1)
		def itemss = [item]
		categoryF = new Category(name:"Padre", children:sons, items:itemss)
		assertFalse categoryF.validate()
		}
	


}
