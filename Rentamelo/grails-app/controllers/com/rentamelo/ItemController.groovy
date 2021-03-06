package com.rentamelo
import org.apache.commons.io.FileUtils
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
		double price = 0
		try{
		price = Double.parseDouble(params.price)
		}
		catch(NumberFormatException e){
			flash.message = "Por favor utiliza un precio valido (solo numeros y un punto)"
			if(price>=100000){
				flash.message = "El precio no puede ser mayor a 100000"
				}
			return render(view: "edit", model: [itemInstance: itemInstance])
			}
		if(price>=100000){
			flash.message = "El precio no puede ser mayor a 100000"
			return render(view: "edit", model: [itemInstance: itemInstance])
			}
		
		itemInstance.pricePerDay=price
        itemInstance.user = currentUser()
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
		boolean changeImage = params.changeImage
        if (itemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (itemInstance.version > version) {
                    
                    itemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'item.label', default: 'Item')] as Object[], "Another item has updated this Item while you were editing")
                    render(view: "edit", model: [itemInstance: itemInstance])
                    return
                }
            }
			double price = 0
			itemInstance.properties = params
			if(changeImage==true){
				def picture = params.photo
				itemInstance.picture = picture.getBytes()
				}
	try{
		price = Double.parseDouble(params.price)
		}
		catch(NumberFormatException e){
			flash.message = "Por favor utiliza un precio valido (solo numeros y un punto)"
			if(price>=100000){
				flash.message = "El precio no puede ser mayor a 100000"
				}
			return render(view: "edit", model: [itemInstance: itemInstance])
			}
		if(price>=100000){
			flash.message = "El precio no puede ser mayor a 100000"
			return render(view: "edit", model: [itemInstance: itemInstance])
			}
            itemInstance.pricePerDay = price
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
                 redirect(action: "index", controller:"user")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])}"
            redirect(action: "index", controller:"user")
        }
    }
	
	def cancel= {
		redirect(action: "index")
		}
	
	def image= {
      def item = Item.get( params.id )
      byte[] image = item.picture 
	 // try{
		  response.outputStream << image
	/*	  }
	  catch(Throwable e){
		  
		  }*/

    }
	
//-------------------------------	
	//-------------------------------
	@Secured(['ROLE_ADMIN','ROLE_USER'])
	def createRent = {
		def itemInstance = Item.findByName(params.itemName)
		if (getCurrentUser()!=itemInstance.user){
		def rentInstance = new Rent()
		rentInstance.properties = params
		return [rentInstance: rentInstance, itemInstance:itemInstance]
		}
		else {
			flash.message = "No puedes rentar tus propios articulos"
			return redirect(action:"index", controller:"user")
			}
	}
	
	
	def saveRent={
		def rent = new Rent(params)
		def user = getCurrentUser()
		def itemInstance = Item.findByName(params.name)
		int numberOfDays = Integer.valueOf(params.numberOfDays)
		//if(itemInstance.isRented==false){ verificar que si alguien m�s rent�
		rent.initializeRent(itemInstance.name, user.username, numberOfDays)
		def totalCost = rent.totalCost
		def address = itemInstance.user.address1
		rent.save()
		if (itemInstance.canBeSent==true){
					sendMail {
			to "${itemInstance.user.email}"
			subject 'Te han rentado un articulo, preparate para enviarlo'
			from "rentameloapp@gmail.com"
			body 'Felicidades! '+rent.rentedByUser+' ha rentado '+rent.itemRented+' por '+rent.daysRented+' dias y debe de pagarte un total de $'+totalCost+" pesos. \nDebes de enviarlo a la direcci�n ${user.address1}"
		 }
			
			flash.message = "El articulo sera enviado a su casa en brevedad, recuerde que debe pagar ${totalCost} pesos"
			return redirect(action:"index", controller:"user")
			}
		else{
			sendMail {
				to "${itemInstance.user.email}"
				subject 'Te han rentado un articulo'
				from "rentameloapp@gmail.com"
				body 'Felicidades! '+rent.rentedByUser+' ha rentado '+rent.itemRented+' por '+rent.daysRented+' dias y debe de pagarte un total de $'+totalCost+' pesos'
			 }
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

