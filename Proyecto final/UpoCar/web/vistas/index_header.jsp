<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            <%@include file="../librerias/bootstrap-3.3.7-dist/css/bootstrap.css" %>
            <%@include file="../css/css.css" %>
            <%@include file="../css/buscaviajes.css" %>
            <%@include file="../css/micuenta.css" %>
            <%@include file="../css/misviajes.css" %>
            <%@include file="../librerias/w3css/w3.css" %>
        </style>
        <title>UpoCar</title>
    </head>
    <body>
        <s:set name="theme" value="'simple'" scope="page" /> <%-- Elimina el estilo de Struts de esta pagina --%>

        <div class="w3-bar w3-border w3-light-grey">
            <a href="#" class="w3-bar-item w3-button">UPOCAR</a>                        
            
            <s:form action="toLogin">
                <s:submit name="toLogin" value="LOGIN" cssClass="w3-bar-item w3-button w3-right"></s:submit>
            </s:form>
            
            <s:form action="toRegistro">
                <s:submit name="toRegistro" value="REGISTRATE" cssClass="w3-bar-item w3-button w3-right"></s:submit>
            </s:form>            
        </div>        