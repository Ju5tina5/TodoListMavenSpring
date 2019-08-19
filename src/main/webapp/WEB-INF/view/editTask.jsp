<%-- 
    Document   : tEditor
    Created on : Aug 15, 2019, 12:30:43 PM
    Author     : Justinas Pekarskis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task Editor</title>
    </head>
    <body>
        <form method="POST" action="saveTask">

            <c:if test="${task!=null}">
                <input name="tId" type="" value="${task.id}">
                <input name="id" type="" value="${todo.id}">
                <input name="complete" type="" value="${task.complete}">
                </c:if>
                <input name="task" placeholder="Task Description" value="${task!=null?task.task:""}">
                <input type="submit" value="save">
                <a href="./">Cancel</a>
        </form>
    </body>
</html>
