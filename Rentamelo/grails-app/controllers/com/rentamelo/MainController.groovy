package com.rentamelo

class MainController {

    def index = {		
		//params.max = Math.min(params.max ? params.int('max') : 10, 100)
		//[itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()] 
		def items = Item.list(sort:"deadLine",order:"asc",max:5)
		
		return [items:items]
	}
	
	def itemList = {

	}
}
