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
  <h1>Por favor elije el reporte que desees ver</h1> <br/>
  <g:link action="userListReport"><h3>Reporte por usuario</h2></g:link>
  <br/> 
  <g:link action="itemReport"><h3>Articulos mas rentados</h2></g:link>
  <br/>
    <g:link action="dateReport" ><h3>Rentas por fecha</h2></g:link> 
  </div>
</body>
</html>