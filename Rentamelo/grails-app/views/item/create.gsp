

<%@ page import="com.rentamelo.Item" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
        <title>Publicar nuevo articulo</title>
     <jv:generateValidation domain="item" display="list" container="valErrors" form="itemForm" ignore="['user','timesRented', 'category', 'isRented', 'canBeSent', 'pricePerDay']"/>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <!-- <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>-->
        </div>
        <div class="body">
            <h1>Publicar nuevo articulo</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${itemInstance}">
            <div id="errors" class="errors" style="display:none;">
                <g:renderErrors bean="${itemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form id="itemForm" name="itemForm" onsubmit="return validateForm(this);" action="save"  enctype="multipart/form-data">
                               <div id="valErrors" class="errors" style="display:none;">
                               </div>
                               
                               <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="item.name.label" default="Titulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${itemInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="photo"><g:message code="item.picture.label" default="Foto" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'picture', 'errors')}">
                                   	<input type="file" name="picture"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="details"><g:message code="item.details.label" default="Detalles" /></label>
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
                                    <label for="canBeSent"><g:message code="item.canBeSent.label" default="Puede ser enviado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'canBeSent', 'errors')}">
                               <g:checkBox name="canBeSent" value="${false}"/>
                                </td>
                            </tr>
                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                               		<span class="button"><a class="home" href="${createLink(uri: '/')}"><g:message code="Cancelar"/></a></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
