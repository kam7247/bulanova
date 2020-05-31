<!DOCTYPE html>
<html lang="en">
<head>
    <title>БлогПост</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<#include "menu.ftlh">

<div class="container" style="max-width: 40em">

    <#if posts?size != 0>
        <h1>Посты <a href="#" class="badge badge-pill badge-primary">${posts?size}</a></h1>
        <#list posts as post>
            <div class="card m-5">
                <img class="d-flex card-img-top" src="${post.poster!"https://avatarko.ru/img/kartinka/7/zhivotnye_volk_arbuz_6664.jpg"}"
                     alt="${post.title}">
                <div class="card-body">
                    <h5 class="card-title">${post.title}</h5>
                    <p class="card-text">${post.description}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Жанр: ${post.genre}</li>
                    <li class="list-group-item">Год: ${post.year}</li>
                </ul>
            </div>
        </#list>
    <#else>
        <h1>На данный момент постов нет</h1>
    </#if>
    <#if authors?size != 0>
        <h1>Авторы <a href="#" class="badge badge-pill badge-primary">${authors?size}</a></h1>
            <#list authors as author>
                        <div class="card m-5">
                        <img class="d-flex card-img-top" src="${author.photoSrc!"https://avatarko.ru/img/kartinka/7/zhivotnye_volk_arbuz_6664.jpg"}"
                             alt="${author.username}">
                        <h3 class="card-title">${author.username}</h3>
                            </div>
            </#list>
    <#else>
    <h1>На данный момент авторов нет</h1>
    </#if>
</div>
</body>
</html>
