package com.rentamelo
import org.compass.core.engine.SearchEngineQueryParseException

class SearchController {
	 def searchableService

def index = { if (!params.q?.trim()) { return [:] } else
	 try{ return [searchResult: searchableService.search(params.q, params)] } 
	 catch (SearchEngineQueryParseException ex) { return [parseException: true] } }

}
