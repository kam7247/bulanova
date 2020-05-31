<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<body>
<#include "menu.ftlh">
<script type="text/javascript">
</script>
<div class="container bootstrap snippet">
    <div class="row">
        <div class="col-sm-10"><h1>Edit Profile</h1></div>
    </div>
    <form method="post" enctype="multipart/form-data" >
        <div class="row">
        <div class="col-sm-3"><!--left col-->
            <div class="text-center">
                <img style="max-width: 15em" src="${profile.photoSrc!"https://avatarko.ru/img/kartinka/7/zhivotnye_volk_arbuz_6664.jpg"}"
                     alt="avatar">
                <h6>Загрузите свой аватар</h6>
                <input type="file" class="text-center center-block file-upload" name="photo">
            </div>

            <#if profile.isAuthor()>
                <div class="panel panel-default">
                    <div class="panel-heading">Посты <i class="fa fa-link fa-1x"></i></div>
                    <#if profile.posts?size == 0>
                        <div class="panel-body">Пока у Вас нет постов</div>
                    <#else>
                        <ul class="list-group list-group-flush">
                            <#list profile.posts as post>
                                <li class="list-group-item">
                                    <a class="card-link" href="/post?id=${post.id}" class="panel-body">${post.title}</a>
                                </li>
                            </#list>
                        </ul>
                    </#if>
                    <#if isItMe>
                        <a class="card-link" href="/profile/post/create" class="button" onclick="validateForm()">Опубликовать пост</a>
                    </#if>
                </div>
            </#if>

        </div><!--/col-3-->
        <div class="col-sm-9">

            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <div class="form" id="registrationForm">
                        <div class="form-group">
                            <label for="first_name"><h4>Username</h4></label>
                            <input type="text" class="form-control" name="username" id="first_name"
                                   value="${profile.username}">
                        </div>
                        <div class="form-group">
                            <label for="email"><h4>Email</h4></label>
                            <input type="email" class="form-control" name="email" id="email"
                                   value="${profile.email}" title="enter your email.">
                        </div>
                        <div class="form-group">
                            <label for="location"><h4>Country</h4></label>
                            <input class="form-control" id="location" value="${profile.country!""}"
                                   title="enter a country" name="country">
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12">
                                <br>
                                <button class="btn btn-lg btn-success" name="submit" type="submit">
                                    <i class="glyphicon glyphicon-ok-sign"></i>
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>

                    <hr>

                </div><!--/tab-pane-->
            </div>
        </div>
            </div>
    </form>

</div>
</body>
</html>