<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />

<title>Elegir fecha</title>
</head>
<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
<g:form>
              <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
  <div class="body">
  
  Fecha de inicio
  <g:datePicker name="startDate" value="${new Date()}" precision="day" years="${2011..2020}"/>
 <br/>
  
  Fecha de finalizacion
  <g:datePicker name="endDate" value="${new Date()}" precision="day" years="${2011..2020}"/>
  
  </div>
                    <div class="buttons">
                    <span class="button"><g:actionSubmit value="Aceptar" action="rentDateReport"/></span>
                               		<span class="button"><g:link action="reportSelection" /><g:message code="Cancelar"/></a></span>
                </div>
  </g:form>
  
</body>
</html>