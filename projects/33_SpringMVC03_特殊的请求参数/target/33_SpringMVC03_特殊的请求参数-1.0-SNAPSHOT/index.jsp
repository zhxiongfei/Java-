
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <div>
        <form action="test2" method="post" enctype="multipart/form-data">
            <input name="username">
            <input name="password">
            <button type="submit">提交</button>
        </form>
    </div>

    <div>
        <form action="test3" method="post" enctype="multipart/form-data">
            <input name="username">
            <input type="file" name="photo">
            <button type="submit">提交</button>
        </form>
    </div>

    <div>
        <form action="test4" method="post" enctype="multipart/form-data">
            <input name="username">
            <input type="file" name="photo1">
            <input type="file" name="photo2">
            <button type="submit">提交</button>
        </form>
    </div>

    <div>
        <form action="test5" method="post" enctype="multipart/form-data">
            <input name="username">
            <input type="file" name="photos">
            <input type="file" name="photos">
            <button type="submit">提交</button>
        </form>
    </div>

    <div>
        <form action="test6" method="post" enctype="multipart/form-data">
            <input name="birthday" type="date">
            <button type="submit">提交</button>
        </form>
    </div>

</body>
</html>