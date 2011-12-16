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
				
				
				user = User.findByUsername('jigd') ?: new User(
					username: 'jigd',
					password: '123456',
					enabled: true,
					fullName: 'jijijiji',
					email: 'maillllll@mailservice.com',
					address1: 'Calle Falsa 123'
					).save(failOnError: true)
					
			if (!user.authorities.contains(role)) {
				SecUserSecRole.create user, role
			}

				def categ = new Category(name:"Electrodomesticos").save()
				categ = new Category(name:"Instrumentos").save()
				categ = new Category(name:"Carros").save()
				categ = new Category(name:"Casas").save()
				
				def item = new Item(name:"Plancha", summary:"Plancha nuevesita",
					details:"Esta plancha esta curada, te la dejo barata",
					category:Category.findByName("Electrodomesticos"),
					dateCreated: new Date(), isSent:true, user:User.findByUsername('admin')).save(failOnError: true)

					item = new Item(name:"Casa en la joya", summary:"Ven y conoce la finura",
						details:"Casa en la joya a solo 10000000000 pesos el dia",
						category:Category.findByName('Casas'),
						dateCreated: new Date(), isSent:true, user:User.findByUsername('admin')).save(failOnError: true)
					
						item = new Item(name:"Violin", summary:"Violin Barato",
							details:"Este es un violin barato pero saca de apuro",
							category:Category.findByName("Instrumentos"),
							dateCreated: new Date(), isSent:true, user:User.findByUsername('admin') ).save(failOnError: true)
					
							item = new Item(name:"El bochomovil", summary:"Un bochito muy particular ",
							details:"Lucete con lo inlucible rentando este bochomovil!",
							dateCreated: new Date(), isSent:true, user:User.findByUsername('admin'), category:Category.findByName('Carros') ).save(failOnError: true)
							
													
							
	}
    def destroy = {
    }
}
