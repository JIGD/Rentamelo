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
					photo:new File('C:/pez1.jpg').getBytes() , dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)

					item = new Item(name:"Otra Cosa", summary:"Este es otro articulo disponible para renta",
						details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
						photo:new File('C:/pez2.jpg').getBytes(), dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)
					item = new Item(name:"Otra Cosa mas", summary:"Este es otro articulo mas disponible para renta",
							details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
							photo:new File('C:/pez3.jpg').getBytes(), dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)
					item = new Item(name:"Una ultima cosa", summary:"Este es el ultimo articulo disponible para renta",
						    details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres",
							photo:new File('C:/pez4.jpg').getBytes(), dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)
					item = new Item(name:"Descripcion larga", summary:"Este articulo tiene mucho texto en el campo detalles. ",
							details:"Aqui detallo muy bien que ondas con lo que quiero rentar sin pasarme de los mil caracteres. Aunque para este articulo en particular, este campo es llenado con mucho texto, con la intencion de ver como se comportan las plantillas de diseño. Igual no debe de pasarse de los mil caracteres.",
							photo:null, dateCreated: new Date(), deadLine: new Date(), isSent:true ).save(failOnError: true)
							
	}
    def destroy = {
    }
}
