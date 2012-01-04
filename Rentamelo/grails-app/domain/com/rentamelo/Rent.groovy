package com.rentamelo

import java.awt.event.ItemListener;
import java.util.Date;

class Rent {

	String itemRented
	String itemOwner
	String rentedByUser //Utilizar el nombre de usuario para esto
	Date dateToReturn //Fecha en que hay que regresarlo, quizá no usarlo hasta que ya se haga el checkout
	Date dateRented
	boolean returned = false
	int daysRented
	double totalCost
	
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
		this.daysRented=daysRented
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
		def rentOwn =Rent.findAllWhere(itemOwner:owner, returned:false)
		return rentOwn
		}
	//-----------------------------------------------------------------------
	//Faltan cosas!!
	//Funciones para reportes!! Recordar que se tienen que hacer listas de montones
	
	static def totalMoneyUsedByUser(String user){
		def rentsByUser = Rent.findAllByRentedByUser(user)
		def rentsCount = rentsByUser.size()
		def totalMoney = 0
		rentsByUser.each {
			totalMoney+=it.totalCost
			}
		System.out.println(totalMoney + " "+rentsCount)
		return [totalMoney, rentsCount]
		}
	
	static def totalMoneyEarnedByUser(String user){
		def rentsByUser = Rent.findAllByItemOwner(user)
		def rentsCount = rentsByUser.size()
		def totalMoney = 0
		rentsByUser.each {
			totalMoney+=it.totalCost
			}
		System.out.println(totalMoney + " "+rentsCount)
		return [totalMoney, rentsCount]
		}
	
	static def reportByItem(String itemName){
		def rentsByItem = Rent.findAllByItemRented(itemName)
		def itemInstance = Item.findByName(itemName)
		def totalMoney = 0
		rentsByItem.each{
			totalMoney+= it.totalCost
			}
		def itemReport = [itemMoney:totalMoney, timesRented:itemInstance.timesRented, itemName:itemName]
		return itemReport
		}
	
	static def reportByOwner(String someUser){
		def itemOwner = User.findByUsername(someUser)
		def itemsByUser = Item.findAllWhere(user:itemOwner)
		def reportList = []
		itemsByUser.each{
			reportList.add(Rent.reportByItem(it.name))
			}
		return reportList
		}
	
	
	static def reportAllUsers(){
		def users = User.findAll()
		def totalUsersStuff
		users.each{
			totalUsersStuff.add(Rent.reportByUser(it.username))
			}
		return [totalUsers:totalUsersStuff]
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
	
	static def mostRentedItems(){
		def itemRentedList = Item.list(sort:'timesRented', order:'desc')
		def reportList = []
		itemRentedList.each{
			reportList.add(Rent.reportByItem(it.name))
			}
		return reportList
		}
	
	static def rentByDate(Date startDate, Date endDate){
		def rentList = Rent.findAllByDateRentedBetween(startDate, endDate)
		}
	
	
	
}
