<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
  <div class="body">
             <%--  	<g:render template="/item/rentOwner" collection="${rentO}" var="rentO"/>     --%>
  				<g:render template="/item/rentUser" collection="${rent}" var="rentU"/> 
  </div>
</body>
</html>