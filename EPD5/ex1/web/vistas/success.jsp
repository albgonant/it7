<%-- 
    Document   : success
    Created on : 12-mar-2018, 18:33:07
    Author     : Gonza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome <s:property value="username" /><s:property value="password" /></h1>
        <s:form action="login">
            <s:submit value="Volver"></s:submit>
        </s:form>  
    </body>
</html>
