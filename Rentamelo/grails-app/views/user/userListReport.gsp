<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  						<g:each in="${userList}" status="i" var="userInstance">
  						
<g:link params="[userName:userInstance.username]" action="userReport" controller="user" ><h2>"${userInstance.username}"</h2></g:link>

                  </g:each>
  </div>
</body>
</html>