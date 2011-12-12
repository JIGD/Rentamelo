class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/login/$action?"(controller: "login")
		"/login/$action?"(controller: "logout")
		
		"/item"(controller:"item")
		"/item/itemByCategory/$id"(controller:"item", action:"itemByCategory")
		
		"/"(controller: "main")
		"/500"(view:'/error')
		"/buscar"(controller: "search")
		"/search"(controller: "search")
	}
}
