﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>小码哥简历管理-留言信息</title>
    <%@ include file="common/head.jsp"%>
</head>

<body class="theme-blue">
    <%@include file="common/middle.jsp" %>

    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>留言信息</h2>
                        </div>
                        <div class="body table-responsive">
                            <div class="menus">
                                <div class="search-box input-group">
                                    <select name="subjectId">
                                        <option value="2">全部</option>
                                        <option value="1">已读</option>
                                        <option value="0">未读</option>
                                    </select>
                                    <div class="form-line input">
                                        <input type="date" name="beginDay" class="form-control" placeholder="开始日期">
                                    </div>
                                    <div class="form-line input">
                                        <input type="date" name="endDay" class="form-control" placeholder="结束日期">
                                    </div>
                                    <span class="input-group-addon">
                                        <i class="material-icons">search</i>
                                    </span>
                                    <div class="form-line keyword">
                                        <input type="text" class="form-control" placeholder="主题、内容等">
                                    </div>
                                    <button type="submit" class="btn bg-blue waves-effect btn-sm">搜索</button>
                                </div>
                            </div>

                            <c:if test="${not empty result.contacts}">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>已读</th>
                                    <th>名称</th>
                                    <th>邮箱</th>
                                    <th>日期</th>
                                    <th>主题</th>
                                    <th>内容</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${result.contacts}" var="contact">
                                    <tr>
                                        <td>
                                            <div class="switch">
                                                <label>
                                                    <c:choose>
                                                        <c:when test="${contact.alreadyRead}">
                                                            <input id="read-${contact.id}" type="checkbox" disabled checked>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input id="read-${contact.id}" type="checkbox" disabled>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <span class="lever switch-col-blue"></span>
                                                </label>
                                            </div>
                                        </td>
                                        <td>${contact.name}</td>
                                        <td>${contact.name}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${contact.createdTime}"/></td>
                                        <td>${contact.subject}</td>
                                        <td>${contact.comment}</td>
                                        <td>
                                            <button type="button" class="btn bg-blue waves-effect btn-xs"
                                                    onclick="view(${contact.json})">
                                                <i class="material-icons">edit</i>
                                                <span>查看</span>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                </table>
                            </c:if>
                            <div class="page-box">
                                <div class="page-size">
                                    共100条10页，每页
                                    <select name="pageSize">
                                        <option value="10">10</option>
                                        <option value="20">20</option>
                                        <option value="30">30</option>
                                        <option value="40">40</option>
                                        <option value="50">50</option>
                                    </select>
                                    条
                                </div>
                                <nav>
                                    <ul class="pagination">
                                        <li class="disabled">
                                            <a>
                                                <i class="material-icons">chevron_left</i>
                                            </a>
                                        </li>
                                        <li class="active"><a>1</a></li>
                                        <li><a href="javascript:void(0);" class="waves-effect">2</a></li>
                                        <li><a href="javascript:void(0);" class="waves-effect">3</a></li>
                                        <li><a href="javascript:void(0);" class="waves-effect">4</a></li>
                                        <li><a href="javascript:void(0);" class="waves-effect">5</a></li>
                                        <li>
                                            <a href="javascript:void(0);" class="waves-effect">
                                                <i class="material-icons">chevron_right</i>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <%@include file="common/footer.jsp"%>
    <script>
        $('.menu .list .contact').addClass('active')
    </script>
</body>

</html>
