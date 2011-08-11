import com.rentamelo.User

class BootStrap {

    def init = { servletContext ->
    
		def user = new User(username:"usuario01",password:"password",fullName:"Qwerty Asdf",address1:"z", email:"correo@email.com")
		user.save(failOnError:true)
		
	}
    def destroy = {
    }
}
