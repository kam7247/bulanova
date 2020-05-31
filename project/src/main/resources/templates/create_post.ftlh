<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Новый пост</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<script>
    $("#form-load-image").dmUploader({
        url: "upload.php",
        onBeforeUpload: function (id) {
            $(".message-load").text("Uploading image");
        },
        onUploadSuccess: function (id, data) {
            if (data) {
                var fullPathToImg = location.protocol + "//" + location.hostname + "/" + data;
                $("#target").attr("src", fullPathToImg);
            } else {
                alert("Error uploading file");
            }
            $(".message-load").text("");
        }
    });
</script>
<script type="text/javascript">
</script>
<body>
<#include "menu.ftlh">
<section>
    <div class="container" style="max-width: 40em">
        <h1>Создать пост</h1>
    <form method="post" enctype="multipart/form-data" class="album-info">
        <div>
            <input id="file-load-field" name="poster" type="file" title="Album's poster">
            <p class="message-load"></p>
        </div>
        <div>
            <div class="form-group">
            <input type="text" class="form-control" name="title"
                       placeholder="Title" title="enter your username if any.">
            </div>
            <div class="form-group">
            <input type="text" class="form-control" name="genre"
                           placeholder="Genre" title="enter your username if any.">
            </div>
            <div class="form-group">
            <input type="number" class="form-control" name="year"
                                   placeholder="Year" title="enter your username if any.">
            </div>
            <div class="form-group">
                <textarea  placeholder="Содержание" name="description"  class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
            </div>
        </div>
        <button class="container-fluid btn btn-success" type="submit" >Отправить</button>
    </form>
        </div>
</section>
</body>
</html>