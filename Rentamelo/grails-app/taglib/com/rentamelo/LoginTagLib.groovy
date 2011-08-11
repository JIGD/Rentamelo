package com.rentamelo

class LoginTagLib {
	static namespace = "login"
	def loginControl = {
		if(session.user){
		  out << "Hello ${session.user.fullName} "
		  out << """[${link(action:"logout", controller:"user"){"Logout"}}]"""
		} else {
		  out << """[${link(action:"login", controller:"user"){"Login"}}]"""
		}
	  }
}
