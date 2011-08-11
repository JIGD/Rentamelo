

<%@ page import="com.rentamelo.Item" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${itemInstance}">
            <div class="errors">
                <g:renderErrors bean="${itemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save"  enctype="multipart/form-data">
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="item.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${itemInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="summary"><g:message code="item.summary.label" default="Summary" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'summary', 'errors')}">
                                    <g:textField name="summary" value="${itemInstance?.summary}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="photo"><g:message code="item.photo.label" default="Photo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'photo', 'errors')}">
                                    <input type="file" id="photo" name="photo" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="deadLine"><g:message code="item.deadLine.label" default="Dead Line" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'deadLine', 'errors')}">
                                    <g:datePicker name="deadLine" precision="day" value="${itemInstance?.deadLine}" default="none" noSelection="['': '']" />
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
                                    <label for="isSent"><g:message code="item.isSent.label" default="Is Sent" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemInstance, field: 'isSent', 'errors')}">
                                    <g:checkBox name="isSent" value="${itemInstance?.isSent}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
