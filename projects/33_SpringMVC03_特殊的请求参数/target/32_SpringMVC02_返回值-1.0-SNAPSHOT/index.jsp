
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="test/dog/jsp1">测试</a>

<input type="text" id="path">
<button id="btn">请求</button>

<script src="jquery.min.js"></script>
<script>
    $('#btn').click(function () {
        const path = $('#path').val()
        const url = '${pageContext.request.contextPath}/' + path
        // 发送AJAX请求
        $.getJSON(url, function (json) {
            console.log(json)
        })
    })
</script>
</body>
</html>