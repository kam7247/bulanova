<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>no-reply</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div>
    <h1><a>Dear ${user.username},</a></h1>
    <img class="d-flex card-img-top" src="https://avatarko.ru/img/kartinka/7/zhivotnye_volk_arbuz_6664.jpg">
    <p style="font-size: 25px;">
        If you try register to BlogPost few time ago, please click to complete registration.
    </p>
    <a href="http://localhost:8080/confirm/email?code=${confirmationCode}"
       style="color: white; background-color: dodgerblue; border-radius: 8px; padding: 16px;  font-size: 40px;">Click</a>

</div>

</body>
</html>