<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head lang="en" >
	<meta charset="UTF-8" />
</head>
<body>
<div id="header">
	<div class="col-md-6 text-right info">
		欢迎您： <span th:text="${userInfo.username}">name</span>
	</div><a href="/logout">退出</a>
</div>
<table width="99%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td width="10%" th:text="${userInfo}"></td>
	</tr>
</table>
</body>
</html>
