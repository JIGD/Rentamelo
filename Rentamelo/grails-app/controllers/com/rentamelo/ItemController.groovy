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
	
	def listByCategory = {
		String categoryName = params.categoryName
		def categoryItems = Item.getByCategory(categoryName)
		[itemInstanceList: categoryItems, itemInstanceTotal: categoryItems.count()]
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
        return [itemInstance: itemInstance, categoryInstanceList: Category.list()]
    }
	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def save = {
        def itemInstance = new Item(params)
        itemInstance.user = currentUser()
		def category = Category.findByName(params.category.name)
		itemInstance.category = category
		if (itemInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'item.label', default: 'Item'), itemInstance.id])}"
            redirect(action: "show", id: itemInstance.id)
        }
        else {
            render(view: "create", model: [itemInstance: itemInstance])
        }
    }

    def show = {
		def currentUser = getCurrentUser()
		def currentUserName = verifyUser(currentUser)
        def itemInstance = Item.get(params.id)
        if (!itemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
            redirect(action: "list")
        }
        else {
           return [itemInstance: itemInstance, userName: currentUserName]
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
      byte[] image = item.picture 
      response.outputStream << image
    }
	
//-------------------------------	
	//-------------------------------
	@Secured(['ROLE_ADMIN','ROLE_USER'])
	def createRent = {
		def rentInstance = new Rent()
		rentInstance.properties = params
		def itemInstance = Item.findByName(params.itemName)
		return [rentInstance: rentInstance, itemInstance:itemInstance]
	}
	
	
	def saveRent={
		def rent = new Rent(params)
		def user = getCurrentUser()
		def itemInstance = Item.findByName(params.name)
		int numberOfDays = Integer.valueOf(params.numberOfDays)
		//if(itemInstance.isRented==false){ verificar que si alguien más rentó
		rent.initializeRent(itemInstance.name, user.username, numberOfDays)
		def totalCost = rent.totalCost
		def address = itemInstance.user.address1
		rent.save()
		if (itemInstance.canBeSent==true){
			//aqui va el método para avisar al usuario por medio de correo
			//
			//
			//
			//
			flash.message = "El articulo sera enviado a su casa en brevedad, recuerde que debe pagar ${costoTotal} pesos"
			return redirect(action:"index", controller:"user")
			}
		else{
					flash.message = "Puede pasar por su articulo a la direccion: ${address} y recuerde que tiene que pagar ${totalCost} pesos"
					return redirect(action:"index", controller:"user")
								}
		/*}
		else{
			def oldRent = Rent.findWhere(itemRented:itemName,returned:false)
			flash.message = "Lo sentimos, alguien mas ha rentado el articulo mientras tu decidias pero no te preocupes por que estara disponible el ${oldRent.dateToReturn}"
			redirect(action:"index", controller:"user")
			}*/
				}
	
	@Secured(['ROLE_ADMIN','ROLE_USER'])
	def itemReturned ={
		def itemName = params.itemName
		def itemInstance = Item.findByName(itemName)
		def currentUser = currentUser()
		if ((currentUser.username.equals(itemInstance.user.username))||currentUser.authorities.equals("ROLE_ADMIN")){
		def rent = Rent.findWhere(itemRented:itemName,returned:false)
		rent.itemReturned()
		flash.message = "El item está listo para volver a rentarse"
		return redirect(action:"index", controller:"user")
		}
		else{
			flash.message = "Acceso invalido"
			return redirect(action:"index", controller:"user")
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	def currentUser(){
		return User.get(springSecurityService.principal.id)
	}
	
	def getCurrentUserName(){
		springSecurityService.currentUser.username
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

