

<%@ page import="com.rentamelo.Item" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
             <jv:generateValidation domain="item" display="list" container="valErrors" form="editItem" ignore="['user','timesRented', 'category', 'isRented', 'canBeSent']"/>
        
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
            <g:hasErrors bean="${itemInstance}">
            <div class="errors">
                <g:renderErrors bean="${itemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" name="editItem"  enctype="multipart/form-data">
            <div id="valErrors" class="errors" style="display:none;">
                               </div>
                <g:hiddenField name="id" value="${itemInstance?.id}" />
                <g:hiddenField name="version" value="${itemInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="item.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'name', 'errors')}">
                                    ${itemInstance?.name}
                                </td>
                            </tr>
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="details"><g:message code="item.details.label" default="Details" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'details', 'errors')}">
                                    <g:textArea name="details" cols="40" rows="5" value="${itemInstance?.details}" />
                                </td>
                            </tr>
                            
                               <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="pricePerDay"><g:message code="item.pricePerDay.label" default="Precio por dia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'precio', 'errors')}">
                                    <g:textArea name="price" cols="10" rows="1" value="${itemInstance?.pricePerDay}" />
                                </td>
                            </tr>
                        
                                                     <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="category"><g:message code="item.category.label" default="Category" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'category', 'errors')}">
                                    <g:select name="category.id" from="${com.rentamelo.Category.list()}" optionKey="id" value="${itemInstance?.category?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="picture"><g:message code="item.picture.label" default="Picture" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'picture', 'errors')}">
                                    <input type="file" id="photo" name="photo" />
                                    <h4>Si deseas modificar la imagen da click en la casilla</h4>
                                    <g:checkBox name="changeImage" value="${false}"/>
                                </td>
                            </tr>
                            
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="canBeSent"><g:message code="item.canBeSent.label" default="Puede ser enviado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'canBeSent', 'errors')}">
                               <g:checkBox name="canBeSent" value="${itemInstance.canBeSent}"/>
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
               		<span class="button"><a class="home" href="${createLink(uri: '/')}"><g:message code="Cancelar"/></a></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
