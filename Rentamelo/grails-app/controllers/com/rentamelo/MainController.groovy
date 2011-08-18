package com.rentamelo

class MainController {

    def index = {		
		//params.max = Math.min(params.max ? params.int('max') : 10, 100)
		//[itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()] 
		def items = Item.findAll()
		
		return [items:items]
	}
	
	def itemList = {

	}
}
