<!DOCTYPE html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <title>${profile.username}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function sendMessage() {
        $.ajax({
            type: "POST",
            url: "/message/send",
            data: {
                "id": ${profile.id},
                "text": $("#message").val()
            },
            success: function () {
                alert("Done");
                $("#close").click();
            }
        })
    }
</script>
<#include "menu.ftlh">
<div class="container">
    <div class="row">
        <div class="col-sm-10"><h1>${profile.username}</h1></div>
    </div>
    <div class="row">
        <div class="col-sm-3"><!--left col-->
            <div class="text-center">
                <img style="max-width: 15em" src="${profile.photoSrc!"https://avatarko.ru/img/kartinka/7/zhivotnye_volk_arbuz_6664.jpg"}"
                     alt="Неизвестная ошибка">
                <h3></h3>
            </div>

            <#if profile.isAuthor()>
                <div class="panel panel-default">
                    <div class="panel-heading"><@spring.message 'profile.page.posts'/><i class="fa fa-link fa-1x"></i>
                    </div>
                    <#if profile.posts?size == 0>
                        <div class="panel-body"><@spring.message 'profile.page.no_posts'/></div>
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
                        <a class="card-link" href="/profile/post/create" class="button"
                           onclick="validateForm()"><@spring.message 'profile.page.release_post'/></a>
                    </#if>
                </div>
            </#if>

        </div><!--/col-3-->
        <div class="col-sm-9">

            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <form class="form" id="registrationForm">
                        <div class="form-group">
                            <label for="first_name"><h4><@spring.message 'profile.page.username'/></h4></label>
                            <h3 name="first_name" id="first_name">${profile.username}</h3>
                        </div>
                        <div class="form-group">
                            <label for="email"><h4><@spring.message 'profile.page.email'/></h4></label>
                            <h3 id="email">${profile.email}</h3>
                        </div>
                        <#if profile.country??>
                            <div class="form-group">
                                <label for="country"><h4><@spring.message 'profile.page.country'/></h4></label>
                                <h3 id="country">${profile.country}</h3>
                            </div>
                        </#if>
                        <#if isItMe>
                            <div class="form-group">
                                <h5><a href="/profile/edit"><@spring.message 'profile.page.edit'/></a></h5>
                            </div>
                        </#if>
                        <#if !isItMe>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <br>
                                    <#if authenticated>
                                        <a href="#" type="button" class="button" data-toggle="modal"
                                           data-target="#myModal">Write a message</a>
                                    <#else>
                                        <a href="/signIn" type="button" class="button">Authenticate to write message</a>
                                    </#if>
                                </div>
                            </div>
                        </#if>
                        <a href="/confirm/phone"><@spring.message 'profile.page.link_phone'/></a>
                    </form>

                    <div class="modal" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Modal title</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Modal body text goes here.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button"
                                            class="btn btn-primary"><@spring.message 'profile.page.save_changes'/></button>
                                    <button type="button" class="btn btn-secondary"
                                            data-dismiss="modal"><@spring.message 'profile.page.cancel'/></button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr>

                </div><!--/tab-pane-->
            </div>

        </div><!--/tab-pane-->
    </div><!--/tab-content-->

</div><!--/col-9-->

<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Message</h4>
            </div>
            <div class="modal-body">
                <p>
                    <textarea class="form-control" name="text" id="message"
                              title="enter your username if any."></textarea>
                </p>
            </div>
            <div class="modal-footer">
                <button id="close" type="button" class="btn btn-default"
                        data-dismiss="modal"><@spring.message 'profile.page.cancel'/></button>
                <button type="submit" class="btn btn-default" onclick="sendMessage()">Send</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>