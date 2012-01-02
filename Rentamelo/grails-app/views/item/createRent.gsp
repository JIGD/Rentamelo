<%@ page contentType="text/html;charset=ISO-8859-1" %>
<%@ page import="com.rentamelo.Item" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Rentar objeto</title>
</head>

<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
  <div class="body">
              <g:form action="saveRent"  enctype="multipart/form-data">
  <h1>Estas intentando rentar: ${itemInstance.name}</h1></br>
  <h1>Este articulo tiene un costo de: ${itemInstance.pricePerDay} pesos por dia</h1>
 <g:select name="numberOfDays" from="${1..30}" value="${fieldValue(bean: rentInstance, field: 'daysRented')}" id="numberOfDays" onChange=algo() />
                               
   <g:hiddenField name="name" value="${itemInstance.name}" />
   <g:hiddenField name="itemInstance" value="${itemInstance}" />
  <g:javascript>
  function algo()
  {
  var days =  $('#numberOfDays').val()
  var price =  $('#numberOfDays').val()
  var cost = days*price;
  var msg = "El costo total de esta renta es de "+days+"   "+price
	document.writeln(msg)
	}
  </g:javascript>
                  <div class="buttons">
                    <span class="button"><g:actionSubmit value="Aceptar" action="saveRent" onclick="return confirm('Estas seguro?');" /></span>
                </div>
                 </g:form>
  </div>
</body>
</html>