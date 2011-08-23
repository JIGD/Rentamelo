package com.rentamelo

class UserController {
	
	static scaffold = User
	
	def login = {}
	
	def items= {
		
		def items = Item.findAllByUser(sec:username,[sort:'deadLine',order:'asc'])
			
	}
	
}
