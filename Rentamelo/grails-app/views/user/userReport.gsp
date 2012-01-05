<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Reporte de usuario</title>
</head>
<body>

  <div class="body">
                  <h1>Este usuario ha puesto y rentado los siguientes articulos</h1>    
						<g:each in="${ownerReport}" status="i" var="itemReport">
<div class="item">
<h2>Nombre del articulo: ${itemReport.itemName}</h1>
<h3>Dinero generado total: $${itemReport.itemMoney}</h3>
<h3>Numero de veces que ha sido rentado: ${itemReport.timesRented}</h3> <br/>
</div>

                  </g:each>
                  <h3>En total este usuario ha ganado $${userMoney[0]} y ha recibido un total de ${userMoney[1]} rentas</h3>
  </div>
</body>
</html>