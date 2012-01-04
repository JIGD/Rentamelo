<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Reportes</title>
</head>
<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
  <div class="body">
  <h1>Por favor elije el reporte que desees ver</h1>
  <g:link><h2>Reporte general - Muestra todos los usuarios con sus articulos y etc</h2></g:link>
  <g:link action="userListReport"><h2>Reporte por usuario - Muestra una lista de usuario para elegir uno y de ahi sacar una lista de reporte por articulos</h2></g:link> 
    <g:link><h2>Reporte monetario - Cuanto dinero han gastado y generado todos los usuarios</h2></g:link> 
      <g:link><h2>Reporte monetario por usuario - Muestra una lista de usuario para elegir uno y ver su propio reporte monetario (Pendiente)</h2></g:link> 
  </div>
</body>
</html>