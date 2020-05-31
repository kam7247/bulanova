<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</head>
<body>
<#include "menu.ftlh">
<div class="container" style="max-width: 40em">
    <h1>Вход</h1>
    <form  method="post">
        <div class="form-group">
            <label for="exampleInputPassword1">Электронная почта</label>
            <input  type="email" class="form-control" id="email" name="email" required >
        </div>
        <div class="form-group">
            <label >Пароль</label>
            <input type="password" class="form-control" id="psw" name="password" required >
        </div>

        <button type="submit" class="container-fluid btn btn-primary" onclick="validateForm()">Войти</button>
    </form>
</div>

</body>
</html>