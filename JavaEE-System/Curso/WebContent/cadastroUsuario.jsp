<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/cadastro.css" />
</head>
<body>
	<h1>Cadastro de usuário</h1>
	<form action="salvarUsuario" method="post">
		<ul class="form-style-1">
			<table>
				<li>
				<tr>
					<td>Id:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${user.id}" class="field-long"></td>
				</tr>
				</li>
				<tr>
					<td>Login:</td>
					<td><input type="text" id="login" name="login"
						value="${user.login}" class="field-long"></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" id="senha" name="senha"
						value="${user.senha}" class="field-long"></td>
				</tr>
			</table>
			<input type="submit" value="Salvar">
		</ul>
	</form>

	<div class="container">
	<table class="responsive-table">
	<caption>Usuarios Cadastrados</caption>
	<tr>
		<th style="text-align: center;">ID</th>
		<th style="text-align: center;">Login</th>
		<th style="text-align: center;">Senha</th>
		<th style="text-align: center;">Exluir</th>
		<th style="text-align: center;">Editar</th>
	</tr>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td><c:out value="${user.id}"></c:out></td>
				<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
				<td><c:out value="${user.senha}"></c:out></td>
				<td><a href="salvarUsuario?acao=delete&id=${user.id}"><img src="resources/excluir.png" width="20px" height="20px" title="Excluir"></img></a></td>
				<td><a href="salvarUsuario?acao=editar&id=${user.id}"><img src="resources/editar.png" width="20px" height="20px" title="Editar"></img></a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>