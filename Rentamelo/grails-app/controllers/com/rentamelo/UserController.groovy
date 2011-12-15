package com.rentamelo
import grails.plugins.springsecurity.Secured

//@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class UserController {
	
	static scaffold = User
	
	def springSecurityService
	
	def login = {}
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def items= {
		
		def items = Item.findAllByUser(sec:username,[sort:'deadLine',order:'asc'])
			
	}

	@Secured(['ROLE_ADMIN','ROLE_USER'])
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


@Secured(['ROLE_ADMIN'])
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create = {
        def userInstance = new User()
        userInstance.properties = params
        return [userInstance: userInstance]
    }

    def save = {
		def role = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)
        def userInstance = new User(params)
        if (userInstance.save(flush: true)) {
			if (!userInstance.authorities.contains(role)) {
				SecUserSecRole.create userInstance, role
			}
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            redirect(controller:"login",action:"index")
        }
        else {
            render(view: "create", model: [userInstance: userInstance])
        }
    }

	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def edit = {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userInstance: userInstance]
        }
    }

	
    def update = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {
                    
                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                    render(view: "edit", model: [userInstance: userInstance])
                    return
                }
            }
            userInstance.properties = params
            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
                redirect(action: "show", id: userInstance.id)
            }
            else {
                render(view: "edit", model: [userInstance: userInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def delete = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            try {
                userInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def show = {
		def currentUser = getCurrentUser()
		def currentUserName = verifyUser(currentUser)
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
			redirect(action: "index")
		}
		else {
			[userInstance: userInstance, userName: currentUserName]
		}
	}
	
	def getCurrentUser(){
		springSecurityService.currentUser
		}
	
	def verifyUser(User loggedUser){
		if(loggedUser==null){
			return ""
			}
		else{
			return loggedUser.username
			}
		}
}
