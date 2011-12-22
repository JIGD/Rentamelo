<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
              	<g:render template="/item/_rentOwner" collection="${rentO}" var="rentO"/>     
  				<g:render template="/item/_rentUser" collection="${rent}" var="rent"/> 
  </div>
</body>
</html>