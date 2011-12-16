
<%@ page import="com.rentamelo.Item" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <g:sortableColumn property="id" title="${message(code: 'item.id.label', default: 'Id')}" />
                        </sec:ifAnyGranted>
                            <g:sortableColumn property="name" title="Nombre" />
                        
                            <g:sortableColumn property="summary" title="Resumen" />
                        

                           
                            <g:sortableColumn property="dateCreated" title="Fecha de creacion" />
                        
                            <g:sortableColumn property="details" title="Detalles" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${itemInstanceList}" status="i" var="itemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <td><g:link action="show" id="${itemInstance.id}">${fieldValue(bean: itemInstance, field: "id")}</g:link></td>
                        </sec:ifAnyGranted>
                            <td>${fieldValue(bean: itemInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: itemInstance, field: "summary")}</td>
                        
                            
                            <td><g:formatDate date="${itemInstance.dateCreated}" /></td>
                        
                            <td>${fieldValue(bean: itemInstance, field: "details")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${itemInstanceTotal}" />
            </div>
        </div>
    </body>
</html>


