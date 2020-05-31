<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<script>
    function validateForm() {
        var password1 = document.getElementById('psw');
        var password2 = document.getElementById('psw1');
        if (password1.value !== password2.value) {
            alert('Убедитесь в правильности пароля');
            return false;
        }
        var password_regex = /[0-9a-zа-я_A-ZА-Я]*[A-Z]+[0-9a-zа-яА-ЯA-Z]*/i;
        if (!(password1.value.length >= 8 && password_regex.test(password1.value))) {
            alert('Слабый пароль')
        }
        var email = document.getElementById('email');
        var email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;
        if (!email_regexp.test(email.value)) {
            alert('Проверьте почту');
            return false;
        }
    }
</script>
<#include "menu.ftlh">
<div class="container" style="max-width: 40em">
    <h1>Регистрация</h1>
<form  method="post">
    <div class="form-group">
        <label for="exampleInputEmail1">Ник</label>
        <input type="text" class="form-control" name="username" required>
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Электронная почта</label>
        <input  type="email" class="form-control" id="email" name="email" required >
        <small  class="form-text text-muted">Требуется для подтверждения аккаунта.</small>
    </div>
    <div class="form-group">
        <label >Пароль</label>
        <input type="password" class="form-control" id="psw" name="password" required >
    </div>
    <div class="form-group">
        <label >Подтвердите пароль</label>
        <input type="password" class="form-control" id="psw1" required >
    </div>
    <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="ckbox1" name="isAuthor" value="true">
        <label for="ckbox1"><span class="text">Вы Автор?</span></label>
    </div>

    <button type="submit" class="container-fluid btn btn-primary" onclick="validateForm()">Зарегистрироваться</button>
</form>
</div>
</body>
</html>