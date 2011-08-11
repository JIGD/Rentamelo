package com.rentamelo

class UserController {
	
	static scaffold = User
	
	def login = {}
	
	def authenticate = {
		def user = User.findByUsernameAndPassword(params.username,params.password)
		if(user){
			session.user = user
			flash.message = "Bienvenido a Rentamelo, ${user.fullName}!"	
			redirect(controller:"user",action:"list")
		}
		else{
			flash.message = "Lo siento, su nombre de usuario y/o contraseña están incorrectos"
			redirect(action:"login")
		}
		
	}
	def logout = {
		flash.message = "Adios, ${user.fullName}"
		session.user = null
		redirect(controller:"user",action:"list")
	}
}
