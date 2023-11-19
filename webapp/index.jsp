<%@ page import="ru.ibs.logic.Model"%>




<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Hello World!</title>
<body>
<h1>Домашняя</h1>
Введите ID пользователя (0 - для вывода всего списка)
<br/>
Доступно: <%
Model model = Model.getInstance();
out.print(model.getUserList().size());
%>
<form method="get" action="get">
<label>ID:
    <input type="text" name="id"><br/>
</label>
<button type="submit">Поиск</button>
</form>

<a href="addUser.html">Создать нового пользователя</a>
</body>
</html>
