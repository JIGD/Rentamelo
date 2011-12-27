package com.rentamelo

import java.util.Date;

class Rent {

	String itemRented
	String itemOwner
	String rentedByUser //Utilizar el nombre de usuario para esto
	Date dateToReturn //Fecha en que hay que regresarlo, quizá no usarlo hasta que ya se haga el checkout
	Date dateRented
	boolean returned = false
	int daysRented
	float totalCost
	
    static constraints = {
		itemRented nullable: false
		rentedByUser nullable: false
		dateToReturn nullable: false
		dateRented nullable: false
    }
	
	//esto debe de ser llamado por el controlador cuando se cree una renta para inicializarla
	void initializeRent(String itemRented, String rentedByUser, int daysRented){
		this.itemRented = itemRented
		this.rentedByUser = rentedByUser
		dateRented = new Date()
		dateToReturn = dateRented.plus(daysRented)
		def itemInstance = Item.findByName(itemRented)
		itemOwner = itemInstance.user.username
		itemInstance.isRented = true
		totalCost =itemInstance.pricePerDay*daysRented
		itemInstance.timesRented++
		}
	
	
	//Cuando el usuario regresa el item el dueño debe de verificarlo
	void itemReturned(){
		def rentedItem = Item.findByName(itemRented)
		rentedItem.isRented = false
		returned = true
		}
	//esto se muestra en el show del que renta
	static def rentsByUser(String user){
		def rents =Rent.findAllWhere(rentedByUser:user, returned:false)
		return rents
		}
	//esto en el show del dueño
	static def rentsByOwner(String owner){
		def rents =Rent.findAllWhere(itemOwner:owner, returned:false)
		[rentsOwner:rents]
		}
	//-----------------------------------------------------------------------
	//Faltan cosas!!
	//Funciones para reportes!! Recordar que se tienen que hacer listas de montones
	static def totalRentsByUser(String user){
		def rentsByUser = Rent.findAllByRentedByUser(user)
		[rentsByUser:rentsByUser]
		}
	
	static def totalRentsByCategory(String category){
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
