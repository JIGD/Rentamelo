
<%@ page import="com.rentamelo.Item" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    	<link href="${resource(dir: 'css', file: 'app.css')}" type="text/css" rel="stylesheet">
    </head>
    <body>
    
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1>Listado de Articulos</h1>
            <!--<g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>-->
            <div> 
            <g:render template="/item/itemCard" bean = "${itemInstance}" var = "item"/>
            </div>
            
             <sec:ifAnyGranted roles="ROLE_ADMIN">
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${itemInstance?.id}" />
                   
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="Editar" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="Borrar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Estas seguro?')}');" /></span>
              
                </g:form>
            </div>
              </sec:ifAnyGranted>
<g:if test = "${itemInstance.user.username.equalsIgnoreCase(userName)}"> 	
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${itemInstance?.id}" />
                   
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="Editar" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="Borrar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Estas seguro?')}');" /></span>
              
                </g:form>
            </div>
            </g:if>
      
        </div>
    </body>
</html>
