<%@ page import="com.rentamelo.Item" %>

<html>
    <head>
        <title>Bienvenido a Rentamelo!</title>
        <meta name="layout" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <style type="text/css" media="screen">

        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;
            
        }
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            list-style-type:none;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.1em;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody {
            background: url(images/leftnav_midstretch.png) repeat-y top;
            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(images/leftnav_btm.png) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(images/leftnav_top.png) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:280px;
            margin-right:20px;
        }
        </style>
    </head>
    <body>
        
        <div id="nav">
            <div class="homePagePanel">
                <div class="panelTop"></div>
                
                <div class="panelBody">

                                
                    
            
            

                    
      
     <sec:ifLoggedIn>
     <g:link url="[action:'index',controller:'user']">Perfil</g:link> <br/>
     <g:link url = "item/create">Crear articulo</g:link><br/>
     <sec:ifAnyGranted roles="ROLE_ADMIN">
         <g:link url="[action:'create',controller:'category']">Crear una categoria</g:link> <br/>
         <g:link url="[action:'list',controller:'user']">Ver lista de usuarios</g:link> <br/>
         <g:link url="[action:'reportSelection',controller:'user']">Ver reportes</g:link> <br/>
     </sec:ifAnyGranted>
          <g:link url="[action:'myRents',controller:'user']">Mis Rentas</g:link> <br/>
 
     <g:link controller = "logout"> Salir</g:link>
     </sec:ifLoggedIn>
        
     <sec:ifNotLoggedIn>           
      Hola, invitado.<br/>
     <g:link controller ="user" action = "create">Inscribirse</g:link><br/>
     <g:link url= "login/auth">Ingresar</g:link><br/>     
     </sec:ifNotLoggedIn>           
                </div>
                <div class="panelBtm">
<br/>
<div class="categorias" >
<h1>Categorias</h1>
<link href="${resource(dir: 'css', file: 'app.css')}" type="text/css" rel="stylesheet">

         <g:render template="/category/CategoryCard" collection="${things[1]}" var="category"/>  			

                </div>
            </div>
        </div>
        </div>
        <div id="pageBody">
            <h1>Bienvenido a Rentamelo, el sitio de rentas en hermosillo!</h1>
            <p> <h2>Buscas algo por solo un momento o tienes algun objeto que no sueles usar? Este es tu lugar!</h2>
            </p>
			
			 <g:form url='[controller: "search", action: "index"]' id="searchableForm" name="searchableForm" method="get">
        	<g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Buscar" />
    		</g:form>

            <div id="controllerList" class="dialog">
                <h2>Articulos destacados</h2>
                <ul>           
                
            	<link href="${resource(dir: 'css', file: 'app.css')}" type="text/css" rel="stylesheet">
            	<g:render template="/item/itemCard" collection="${things[0]}" var="item"/>     
        		
                </ul>
            </div>
    </div>
    </body>
</html>
