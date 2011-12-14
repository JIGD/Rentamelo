

<%@ page import="com.rentamelo.Category" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${categoryInstance}">
            <div class="errors">
                <g:renderErrors bean="${categoryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${categoryInstance?.id}" />
                <g:hiddenField name="version" value="${categoryInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="parent"><g:message code="category.parent.label" default="Parent" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: categoryInstance, field: 'parent', 'errors')}">
                                    <g:select name="parent.id" from="${com.rentamelo.Category.list()}" optionKey="id" value="${categoryInstance?.parent?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                            
                            </sec:ifAnyGranted>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="children"><g:message code="category.children.label" default="Children" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: categoryInstance, field: 'children', 'errors')}">
                                    
<ul>
<g:each in="${categoryInstance?.children?}" var="c">
    <li><g:link controller="category" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="category" action="create" params="['category.id': categoryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'category.label', default: 'Category')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="category.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: categoryInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${categoryInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="items"><g:message code="category.items.label" default="Items" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: categoryInstance, field: 'items', 'errors')}">
                                    
<ul>
<g:each in="${categoryInstance?.items?}" var="i">
    <li><g:link controller="item" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="item" action="create" params="['category.id': categoryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'item.label', default: 'Item')])}</g:link>

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
