<!DOCTYPE html>
<html lang="en">
<head>
    <title>Поиск</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="navbar-header">
        <a class="navbar-brand bg-light" href="/">БлогПост</a>
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Мой профиль</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0"  action="/search">
            <div class="input-group">
                <div class="input-group-prepend">
                    <button class="btn btn-outline-secondary" onclick="f()" type="submit" id="button-addon1">Поиск</button>
                </div>
                <input class="form-control mr-sm-2" name="query" id="query" type="text"
                        <#if !$query??>
                            value="${query}"
                        </#if>">
            </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <#if !authenticated>
                <li class="nav-item"><a href="/login" class="nav-link">Войти</a></li>
                <li class="nav-item"><a href="/join" class="nav-link" >Зарегистрироваться</a></li>
            <#else>
                <form action="/logout" method="post">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><a>
                            Выйти</a>
                    </button>
                </form>
            </#if>
        </ul>
    </div>
</nav>
<div class="container" style="max-width: 40em" id="res">
    <script type="application/javascript">
        $(document).ready(f());

        function f() {
            if ($("#query").val().length >= 1) {
                $.ajax({
                    url: "/search/ajax",
                    data: {"query": $("#query").val()},
                    dataType: "json",
                    success: function (msg) {
                        if (msg.length > 0) {
                            $("#res").html("");
                            for (var i = 0; i < msg.length; i++) {
                                printItem(msg[i])
                            }
                        } else {
                            $("#res").html("<h1>Не найдено</h1>");
                        }
                    }
                })
            } else {
                $("#res").html("");
            }
        }

        function printItem(item) {
            $("#res").append("<div class=\"card m-5\">" +
                "<img class=\"d-flex card-img-top\" src=\"" + item.poster + "\" alt=\"" + item.title + "\" >" +
                "<p class='card-text'>"+item.description+"</p>"+
                "<a class='card-link' href=\"/post?id=" + item.id + "\" <h3>" + "перейти к посту" + "</h3></a>" +
                "</div>");
        }
    </script>
</div>
</body>
</html>