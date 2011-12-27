package com.rentamelo
import grails.plugins.springsecurity.SecurityConfigType
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class MainController {

    def index = {		
		def items = Item.getItems()
		def categories = Category.list(sort:"name");
		def things =[items, categories];
		return [things:things]
	
	}
	
	def itemList = {

	}
}
