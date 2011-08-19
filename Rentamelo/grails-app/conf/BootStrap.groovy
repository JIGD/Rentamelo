import com.rentamelo.User
import com.rentamelo.SecUser
import com.rentamelo.SecRole
import com.rentamelo.SecUserSecRole
import com.rentamelo.*

class BootStrap {
	def springSecurityService
    def init = { servletContext ->
		
		def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)
		
		def adminUser = User.findByUsername('admin') ?: new User(
							username: 'admin',
							password: 'admin',
							//password: springSecurityService.encodePassword('admin'),
							enabled: true,
							fullName: 'Cosme Fulanito',
							email: 'mail@mailservice.com',
							address1: 'Calle Falsa 123'
							).save(failOnError: true) 
							
					if (!adminUser.authorities.contains(adminRole)) {
						SecUserSecRole.create adminUser, adminRole
					}
			adminUser = User.findByUsername('usuario') ?: new User(
						username: 'usuario',
						//password: 'admin',
						password: springSecurityService.encodePassword('password'),
						enabled: true,
						fullName: 'Cosme Fulanito',
						email: 'mail2@mailservice.com',
						address1: 'Calle Falsa 123'
						).save(failOnError: true)
						
				if (!adminUser.authorities.contains(adminRole)) {
					SecUserSecRole.create adminUser, adminRole
				}
				
				def item = new Item(name:"algo", summary:"Este es un item que quiero rentar",
					details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
					id:1231, photo:123, dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)

					item = new Item(name:"Otra Cosa", summary:"Este es otro articulo disponible para renta",
						details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
						id:1232, photo:123, dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)
					item = new Item(name:"Otra Cosa mas", summary:"Este es otro articulo mas disponible para renta",
							details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
							id:1233, photo:123, dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)
					item = new Item(name:"Una ultima cosa", summary:"Este es el ultimo articulo disponible para renta",
						    details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
							id:1234, photo:123, dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)
							
	}
    def destroy = {
    }
}
