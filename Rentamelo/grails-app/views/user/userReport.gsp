<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Reporte de usuario</title>
</head>
<body>
  <div class="body">
                      <g:each in="${userReport}" status="i" var="itemReport">
                      
						<g:each in="${itemReport}" status="i" var="itemStuff">
						<g:render template="itemReportCard" collection="${itemStuff}" var="itemInfo"/> 


                  </g:each>
                    </g:each>
  </div>
</body>
</html>