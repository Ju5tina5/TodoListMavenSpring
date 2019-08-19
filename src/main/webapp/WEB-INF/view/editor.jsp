<%-- 
    Document   : editor
    Created on : Aug 15, 2019, 9:18:33 AM
    Author     : Justinas Pekarskis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editor</title>
    </head>
    <body>
       <form method="POST" action="save" >
            <c:if test="${todo!=null}">
                <input name="id" type="hidden" value="${todo.id}">
                 <input name="complete" type="hidden" value="${todo.complete}">
            </c:if>
            <input name="todo" placeholder="What is it?" value="${todo!= null?todo.todo:""}">
            <input type="submit" value="save">
            <a href="./">Cancel</a>
            href="edit?id=${t.id}"
        </form>
    </body>
</html>
