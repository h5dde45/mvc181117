<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<p>Name: ${user.name}</p>
<p>Password: ${user.password}</p>
<p>Admin: ${user.admin}</p>
<p>Locale: ${locale}</p>

<form method="post" action="uploadFile" enctype="multipart/form-data">
    File to load:
    <input type="file" name="file"><br/>
    <input type="submit" value="Upload">
    Press here to upload file
</form>

</body>
</html>
