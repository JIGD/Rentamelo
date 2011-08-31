package com.rentamelo
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class UserController {
	
	static scaffold = User
	
	def springSecurityService
	
	def login = {}
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def items= {
		
		def items = Item.findAllByUser(sec:username,[sort:'deadLine',order:'asc'])
			
	}
	
	
	@Secured(['ROLE_ADMIN'])
	def index = {
		def userInstance =currentUser()
		if (!userInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
		else {
			[userInstance: userInstance]
		}
	
	
}
	def currentUser(){
		return User.get(springSecurityService.principal.id)
	   }
}
