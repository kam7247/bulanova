<!DOCTYPE html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <title>Menu nav</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
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
                <a class="nav-link" href="/"><@spring.message 'menu.home'/></a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="/profile"><@spring.message 'menu.profile'/></a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0"  action="/search">
            <div class="input-group">
                <div class="input-group-prepend">
                    <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Поиск</button>
                </div>
                <input class="form-control mr-sm-2" name="query" type="text"
                       placeholder="<@spring.message 'menu.search_hint'/>">
            </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <#if !authenticated>
                <li class="nav-item"><a class="nav-link" href="/signUp"> <@spring.message 'menu.sign_up'/>
                    </a></li>
                <li class="nav-item"><a class="nav-link" href="/signIn"> <@spring.message 'menu.sign_in'/>
                    </a></li>
            <#else>
                <form action="/logout" method="post">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><a>
                                   <@spring.message 'menu.logout'/></a>
                    </button>
                </form>
            </#if>
        </ul>
    </div>
</nav>
</body>
</html>
