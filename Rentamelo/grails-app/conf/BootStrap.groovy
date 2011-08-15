import com.rentamelo.User
import com.rentamelo.SecUser

class BootStrap {
	def springSecurityService
    def init = { servletContext ->
		
		def user = new User(username:"usuario01",password:"password",fullName:"Qwerty Asdf",address1:"z", email:"correo@email.com")
		user.save(failOnError:true)
		
		def SecUser secUser = new SecUser(username: 'usuario01', 
										  password: springSecurityService.encodePassword('password'),
										  enabled:true).save(failOnError:true)
		
	    secUser = new SecUser(username: 'admin',
								      password: springSecurityService.encodePassword('admin'),
							          enabled:true).save(failOnError:true)
			
	}
    def destroy = {
    }
}
