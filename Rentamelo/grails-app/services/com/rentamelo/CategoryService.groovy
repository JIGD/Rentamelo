package com.rentamelo

class CategoryService {

    static transactional = true

    def ancestorList()  {
		def ancestors = Category.list(fetch:[parent:null])
		return ancestors
		}
		
		}
