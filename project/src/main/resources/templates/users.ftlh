<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Пользователи</title>
</head>
<body>
<section>
<table class="table">
    <tr>
        <th>Имя</th>
        <th>Почта</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>${user.email}</td>
        </tr>
    </#list>
</table>
</section>

</body>
</html>