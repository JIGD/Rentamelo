class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/login/$action?"(controller: "login")
		"/login/$action?"(controller: "logout")
		
		"/"(controller: "main")
		"500"(view:'/error')
	}
}
