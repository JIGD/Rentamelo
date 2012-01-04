<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Reporte por fecha</title>
</head>
<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>

  <div class="body">
  						<g:each in="${rentsByDate}" status="i" var="rentInstance">
<div class="item">
<h2>Nombre del articulo: ${rentInstance.itemRented}</h1>
<h3>Persona que realizo la renta: ${rentInstance.rentedByUser}</h3>
<h3>Dueño del articulo: ${rentInstance.itemOwner}</h3>
<h3>Fecha en que se realizo la renta: <g:formatDate date="${rentInstance.dateRented}" type="date" style="MEDIUM"/></h3>
<h3>Cantidad de dias que fue rentado el articulo: $${rentInstance.daysRented}</h3>
<h3>Dinero generado por la renta: $${rentInstance.totalCost}</h3>
</div>

                  </g:each>
  </div>
  

</body>
</html>