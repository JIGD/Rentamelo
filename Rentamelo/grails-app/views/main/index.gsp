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
        <g:form url='[controller: "search", action: "index"]' id="searchableForm" name="searchableForm" method="get">
        <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Buscar" />
    	</g:form>
        
        <div id="nav">
            <div class="homePagePanel">
                <div class="panelTop"></div>
                <div class="panelBody">

                                
                    
            

                    
      
     <sec:ifLoggedIn>
     <li>Hola, <sec:username/></li> 
     <li><g:link controller = "logout">Salir</g:link></li>
     </sec:ifLoggedIn>
        
     <sec:ifNotLoggedIn>           
     <li>Hola, invitado.</li> 
     <li><g:link controller = "signup">Inscribirse</g:link></li>
     <li><g:link url= "login/auth">Ingresar</g:link></li>     
     </sec:ifNotLoggedIn>           
                
                <h1>Lista de Categorias:</h1>
                 <ul>
                        <li>Categoria 1</li>
                        <li>Categoria 2</li>
                        <li>Categoria 3</li>
                        <li>Otra Categoría</li>
                </ul>
                
                </div>
                <div class="panelBtm"></div>
            </div>
        </div>
        <div id="pageBody">
            <h1>Bienvenido a Rentamelo!</h1>
            <p> Aqui va una descripcion de la aplicacion y que es lo que hace, etc. El punto es que sea concisa, pero que de una buena idea de que puede 
            	esperar el usuario de la aplicacion. 
            </p>

            <div id="controllerList" class="dialog">
                <h2>Artículos destacados</h2>
                <ul>
            
            	<link href="${resource(dir: 'css', file: 'app.css')}" type="text/css" rel="stylesheet">
            	<div class="span-20"> 
            	<g:render template="itemCard" collection="${items}" var="item"/>
        		</div>
               
            <!--  <div class="itemList">
                <table>
                    <tbody>
                    <g:each in="${itemInstanceList}" status="i" var="itemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        
                            <td>${fieldValue(bean: itemInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: itemInstance, field: "summary")}</td>
                        
                            <td>${fieldValue(bean: itemInstance, field: "photo")}</td>
                            
                            <td><g:formatDate date="${itemInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${itemInstance.deadLine}" /></td>
                        
                            <td>${fieldValue(bean: itemInstance, field: "details")}</td>
                        	
                        	
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>  -->
                
                    	Aqui se colocaria una lista de artículos elegidos ya sea al azar o los que sean de usuarios con más actividad, etc. En caso de que el 
                    	usuario haya ingresado, se podrían listar sus artículos o articulos que sean recomendados para el usuario en particular.   
                </ul>
            </div>
        </div>
    </body>
</html>
