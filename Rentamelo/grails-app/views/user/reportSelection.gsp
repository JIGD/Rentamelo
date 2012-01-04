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
  <g:link action="userListReport"><h2>Reporte por usuario - Muestra una lista de usuario para elegir uno y de ahi sacar una lista de reporte por articulos</h2></g:link>
  <br/> 
  <g:link action="itemReport"><h2>Articulos más rentados - Muestra todos los articulos en orden de más rentados con su respectivo dinero generado</h2></g:link>
  <br/>
    <g:link action="dateReport" ><h2>Rentas por fecha</h2></g:link> 
  </div>
  <div class="buttons">
                      <span class="button"><g:actionSubmit value="Aceptar" action="saveRent" onclick="return confirm('Estas seguro?');" /></span>
                               		<span class="button"><a class="home" href="${createLink(uri: '/')}"><g:message code="Cancelar"/></a></span>
                </div>
</body>
</html>