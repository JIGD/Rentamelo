import com.rentamelo.User
import com.rentamelo.SecUser
import com.rentamelo.SecRole
import com.rentamelo.SecUserSecRole
import com.rentamelo.*

class BootStrap {
	def springSecurityService
    def init = { servletContext ->
		
		def role = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)
     		
		
		def user = User.findByUsername('admin') ?: new User(
							username: 'admin',
							password: 'admin',
							//password: springSecurityService.encodePassword('admin'),
							enabled: true,
							fullName: 'Cosme Fulanito',
							email: 'mail@mailservice.com',
							address1: 'Calle Falsa 123'
							).save(failOnError: true) 
							
					if (!user.authorities.contains(role)) {
						SecUserSecRole.create user, role
					}
			
			
			role = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)
			user = User.findByUsername('usuario') ?: new User(
						username: 'usuario',
						password: 'password',
						enabled: true,
						fullName: 'Cosme Fulanito',
						email: 'mail2@mailservice.com',
						address1: 'Calle Falsa 123'
						).save(failOnError: true)
						
				if (!user.authorities.contains(role)) {
					SecUserSecRole.create user, role
				}

				
				def item = new Item(name:"algo", summary:"Este es un item que quiero rentar",
					details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
					dateCreated: new Date(), deadLine: new Date(), isSent:true, user:User.findByUsername('admin')).save(failOnError: true)

					item = new Item(name:"Otra Cosa", summary:"Este es otro articulo disponible para renta",
						details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
						dateCreated: new Date(), deadLine: new Date(), isSent:true, user:User.findByUsername('admin') ).save(failOnError: true)
					item = new Item(name:"Otra Cosa mas", summary:"Este es otro articulo mas disponible para renta",
							details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
							dateCreated: new Date(), deadLine: new Date(), isSent:true, user:User.findByUsername('admin') ).save(failOnError: true)
					item = new Item(name:"Descripcion larga", summary:"Este articulo tiene mucho texto en el campo detalles. ",
							details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres. Aunque para este articulo en particular, este campo es llenado con mucho texto, con la intencion de ver como se comportan las plantillas de diseño. Igual no debe de pasarse de los mil caracteres.",
							dateCreated: new Date(), deadLine: new Date(), isSent:true, user:User.findByUsername('admin') ).save(failOnError: true)
													
							
	}
    def destroy = {
    }
}
