<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Articulos mas rentados</title>

</head>
<body>
  <div class="body">
  						<g:each in="${mostRentedItems}" status="i" var="itemReport">
<div class="item">
<h2>Nombre del articulo: ${itemReport.itemName}</h1>
<h3>Dinero generado total: $${itemReport.itemMoney}</h3>
<h3>Numero de veces que ha sido rentado: ${itemReport.timesRented}</h3> <br/>
</div>

                  </g:each>
  </div>
</body>
</html>