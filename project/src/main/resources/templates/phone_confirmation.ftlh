<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Телефон</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<#include "menu.ftlh">
<div class="container" style="max-width: 40em">
    <h1>Вход</h1>
    <form  method="post" action="${action}">
        <div class="form-group">
            <label for="exampleInputPassword1">Телефон</label>
            <input  type="tel" class="form-control" placeholder="89999999999"  name="phoneNumber" required >
        </div>
        <div class="form-group">
            <label >Код</label>
            <input type="password" class="form-control" placeholder="1234" id="psw" name="code" required >
        </div>
        <button type="submit" class="container-fluid btn btn-primary" onclick="validateForm()">Отправить</button>
    </form>

</div>
</body>
</html>