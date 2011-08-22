
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'item.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'item.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="summary" title="${message(code: 'item.summary.label', default: 'Summary')}" />
                        
                            <g:sortableColumn property="photo" title="${message(code: 'item.photo.label', default: 'Photo')}" /> 
                           
                            <g:sortableColumn property="dateCreated" title="${message(code: 'item.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="deadLine" title="${message(code: 'item.deadLine.label', default: 'Dead Line')}" />
                        
                            <g:sortableColumn property="details" title="${message(code: 'item.details.label', default: 'Details')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${itemInstanceList}" status="i" var="itemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${itemInstance.id}">${fieldValue(bean: itemInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: itemInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: itemInstance, field: "summary")}</td>
                        
                            <td><img src="${createLink(controller:'item', action:'image', id: itemInstance.id)}"/></td>
                            
                            <td><g:formatDate date="${itemInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${itemInstance.deadLine}" /></td>
                        
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


