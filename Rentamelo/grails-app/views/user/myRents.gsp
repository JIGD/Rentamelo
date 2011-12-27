<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Mis rentas</title>
</head>
<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
  <div class="body">
  				<h1>Cosas que has rentado:</h1>
  				<g:render template="/item/rentUser" collection="${rent}" var="rentU"/> 
  				<h1>Cosas que te han rentado:</h1>
  				<g:render template="/item/rentOwner" collection="${rentO}" var="rentOwn"/>
  				
  </div>
</body>
</html>