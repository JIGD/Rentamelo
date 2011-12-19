package com.rentamelo

import java.util.Date;

class Rent {

	String itemRented
	String rentedByUser //Utilizar el nombre de usuario para esto
	Date dateToReturn //Fecha en que hay que regresarlo, quizá no usarlo hasta que ya se haga el checkout
	Date dateRented
	int daysRented
	boolean returned = false
	float totalCost
	
    static constraints = {
		itemRented nullable: false
		rentedByUser nullable: false
		dateToReturn nullable: false
		dateRented nullable: false
    }
	
	
	//esto debe de ser llamado por el controlador cuando se cree una renta para inicializarla
	def initializeRent(String itemRented, String rentedByUser, int daysRented){
		this.itemRented = itemRented
		this.rentedByUser = rentedByUser
		dateRented = new Date()
		dateToReturn = dateRented.plus(daysRented)
		def itemInstance = Item.findByName(itemRented)
		totalCost =itemInstance.pricePerDay*daysRented
		itemInstance.timesRented++
		itemInstance.isRented = true
		return [canBeSent:itemInstance.canBeSent, address:itemInstance.user.address1]
		}
	
	
	//Cuando el usuario regresa el item el dueño debe de verificarlo
	void itemReturned(){
		def rentedItem = Item.findByName(itemRented)
		rentedItem.isRented = false
		returned = true
		}
	//esto se muestra en el show del que renta
	def public static rentsByUser(String user){
		def rents= Rent.findAllWhere(rentedByUser:user, returned:false)
		[rentsUser:rents]
		}
	//esto en el show del dueño
	def public static rentsByOwner(String owner){
		def userInstance = User.findByUsername(owner)
		def itemsByUser = Item.findAllWhere(user:userInstance, isRented:true)
		def totalRents = []
		itemsByUser.each{
			totalRents.add(Rent.findAllWhere(itemRented:it.name, returned:false))
			}
		[rentsOwner:totalRents]
		}
	//-----------------------------------------------------------------------
	//Faltan cosas!!
	//Funciones para reportes!! Recordar que se tienen que hacer listas de montones
	def public static totalRentsByUser(String user){
		def rentsByUser = Rent.findAllByRentedByUser(user)
		def numberOfRentsByUser = rentsByUser.count
		[numberOfRentsByUser:numberOfRentsByUser]
		}
	
	def public static totalRentsByCategory(String category){
		def itemListByCategory = Item.findAllByCategory(category)
		def listOfRents
		def listOfRentsByCategory
		itemListByCategory.each {
		listOfRents = Rent.findAllByItemRented(it.name)
		listOfRentsByCategory.add(itemRents:listOfRents)
		}
		[totalRentsByCategory:listOfRentsByCategory]
		}
	
	
	
}
