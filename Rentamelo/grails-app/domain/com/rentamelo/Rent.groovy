package com.rentamelo

import java.util.Date;

class Rent {

	String itemRented
	String rentedByUser //Utilizar el nombre de usuario para esto
	Date dateToReturn //Fecha en que hay que regresarlo, quizá no usarlo hasta que ya se haga el checkout
	Date dateRented
	boolean returned = false
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
		totalCost =itemInstance.pricePerDay*daysRented
		itemInstance.timesRented++
		}
	
	
	//Cuando el usuario regresa el item el dueño debe de verificarlo
	void itemReturned(){
		returned = true
		}
	
	def rentsByUser(String user){
		Rent.findAllByRentedByUserAndReturned(user, false)
		}
	
	
	
	
}
