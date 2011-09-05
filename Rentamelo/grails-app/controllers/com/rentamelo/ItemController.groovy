package com.rentamelo
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SecurityConfigType
//grails.plugins.springsecurity.securityConfigType = SecurityConfigType.Annotation

//@Secured(['ROLE_USER','ROLE_ADMIN'])
class ItemController {
	
	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	@Secured(['ROLE_ADMIN'])
    def index = {
		redirect(action: "list", params: params)
    }

   @Secured(['ROLE_ADMIN'])
	def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
      [itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()]
    	
	}
	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def create = {
        def itemInstance = new Item()
        itemInstance.properties = params
        return [itemInstance: itemInstance]
    }
	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def save = {
        def itemInstance = new Item(params)
        itemInstance.user = currentUser()
		if (itemInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'item.label', default: 'Item'), itemInstance.id])}"
            redirect(action: "show", id: itemInstance.id)
        }
        else {
            render(view: "create", model: [itemInstance: itemInstance])
        }
    }
	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def show = {
        def itemInstance = Item.get(params.id)
        if (!itemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
            redirect(action: "list")
        }
        else {
            [itemInstance: itemInstance]
        }
    }
	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def edit = {
        def itemInstance = Item.get(params.id)
        if (!itemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [itemInstance: itemInstance]
        }
    }
	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def update = {
        def itemInstance = Item.get(params.id)
        if (itemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (itemInstance.version > version) {
                    
                    itemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'item.label', default: 'Item')] as Object[], "Another item has updated this Item while you were editing")
                    render(view: "edit", model: [itemInstance: itemInstance])
                    return
                }
            }
            itemInstance.properties = params
            if (!itemInstance.hasErrors() && itemInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'item.label', default: 'Item'), itemInstance.id])}"
                redirect(action: "show", id: itemInstance.id)
            }
            else {
                render(view: "edit", model: [itemInstance: itemInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
            redirect(action: "list")
        }
    }
	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def delete = {
        def itemInstance = Item.get(params.id)
        if (itemInstance) {
            try {
                itemInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def image= {
		def item = Item.get( params.id )
		def attachments = item.attachments
		def photo = attachments[0]
		response.getOutputStream().write(photo.getBytes())
		response.getOutputStream().flush()
    }
	
	def currentUser(){
		return User.get(springSecurityService.principal.id)
	}

}

