<%-- 
    Document   : index
    Created on : Aug 15, 2019, 9:18:17 AM
    Author     : Justinas Pekarskis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos</title>
        <style><%@include file="/WEB-INF/css/main.css"%></style>
        <script><%@include file="/WEB-INF/js/function.js"%></script>
    </head>
    <body>
        <h1>What to Do:</h1>
        <div class="container"> 
            <ul>
                <c:forEach var="t" items="${todos}">
                    <li>
                        <h2 id="todoname${t.id}"> ${t.todo} </h2>
                        <c:choose>
                            <c:when test="${t.complete != null}">
                                Completed:
                                <fmt:formatDate pattern = "yyyy/MM/dd" value = "${t.complete}" />
                                &#9989;
                                <a href="cancel?id=${t.id}">Still not done</a>
                            </c:when> 
                            <c:otherwise>
                                <a href="complete?id=${t.id}">Done</a>
                            </c:otherwise>
                        </c:choose>
                        <a onclick="editForm(${t.id})">Edit To do name</a>
                        <a href="delete?id=${t.id}">Delete</a>    
                        <a href="editTask?id=${t.id}">+</a>      
                        <h4> Tasks:</h4>
                        <ul>
                            <c:forEach var="tt" items="${tasks}">
                                <c:if test="${t.id == tt.todoId.id}">
                                    <li>
                                        ${tt.task}
                                        <c:choose>
                                            <c:when test="${tt.complete != null}">
                                                Completed:
                                                <fmt:formatDate pattern = "yyyy/MM/dd" value = "${tt.complete}" />
                                                &#9989;
                                                <a href="cancelTaskCompetion?id=${tt.todoId.id}&tId=${tt.id}">Still not done</a>
                                            </c:when> 
                                            <c:otherwise>
                                                <a href="completeTask?id=${tt.todoId.id}&tId=${tt.id}">Done</a>
                                            </c:otherwise>
                                        </c:choose>
                                        <a href="editTask?id=${tt.todoId.id}&tId=${tt.id}">Edit Task</a>
                                        <a href="deleteTask?tId=${tt.id}">Delete Task</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
            <a id="newtodo" onclick="addForm()">Add To Do</a>
        </div>
    </body>
</html>
