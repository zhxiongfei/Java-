﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>小码哥简历管理-获奖成就</title>
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
                        <h2>获奖成就</h2>
                    </div>
                    <div class="body table-responsive">
                        <div class="menus">
                            <div class="buttons">
                                <button type="button" class="btn bg-blue waves-effect btn-sm"
                                        onclick="add()">
                                    <i class="material-icons">add</i>
                                    <span>添加</span>
                                </button>
                                <button type="button"
                                        class="btn bg-pink waves-effect btn-sm removeAll disabled"
                                        disabled
                                        onclick="removeAll()">
                                    <i class="material-icons">delete</i>
                                    <span>删除选中</span>
                                </button>
                            </div>
                        </div>

                        <c:if test="${not empty awards}">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>
                                        <div class="switch">
                                            <label><input type="checkbox"><span class="lever switch-col-blue"></span></label>
                                        </div>
                                    </th>
                                    <th>名称</th>
                                    <th>图片</th>
                                    <th>简介</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <form id="remove-form" method="post" action="${ctx}/award/remove">
                                    <c:forEach items="${awards}" var="award">
                                        <tr>
                                            <td>
                                                <div class="switch">
                                                    <label><input name="id" type="checkbox" value="${award.id}"><span class="lever switch-col-blue"></span></label>
                                                </div>
                                            </td>
                                            <td>${award.name}</td>
                                            <td>
                                                <img src="${ctx}/${award.image}">
                                            </td>
                                            <td>${award.intro}</td>
                                            <td>
                                                <button type="button" class="btn bg-blue waves-effect btn-xs"
                                                        onclick="edit(${award.JSON})">
                                                    <i class="material-icons">edit</i>
                                                    <span>编辑</span>
                                                </button>
                                                <button type="button" class="btn bg-pink waves-effect btn-xs"
                                                        onclick="remove('${award.id}', '${award.name}')">
                                                    <i class="material-icons">delete</i>
                                                    <span>删除</span>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </form>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!--  add-form-box  -->
<div class="modal fade" id="add-form-box" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加获奖成就=</h4>
            </div>
            <div class="modal-body">
                <form class="form-validation" method="post" enctype="multipart/form-data" action="${ctx}/award/save">
                    <input style="display :none" type="text" name="id">
                    <input style="display :none" type="text" name="image">
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 form-control-label">
                            <label>图片</label>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-9 col-xs-9">
                            <div class="form-group">
                                <div class="fileinput fileinput-new" data-provides="fileinput">
                                    <div class="fileinput-new thumbnail">
                                        <img src="${ctx}/asset/admin/img/noimage.png" alt="">
                                    </div>
                                    <div class="fileinput-preview fileinput-exists thumbnail"></div>
                                    <i class="material-icons clear fileinput-exists" data-dismiss="fileinput">close</i>
                                    <input type="file" name="imageFile" accept="image/*">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 form-control-label">
                            <label for="name">名称</label>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-9 col-xs-9">
                            <div class="form-group">
                                <div class="form-line">
                                    <input type="text" id="name" name="name" maxlength="20" class="form-control"
                                           placeholder="名称"
                                           required>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 form-control-label">
                            <label for="intro">简介</label>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-9 col-xs-9">
                            <div class="form-group">
                                <div class="form-line">
                                    <textarea name="intro" maxlength="1000" id="intro" cols="30" rows="5" class="form-control no-resize" placeholder="简介"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-3 col-xs-offset-3">
                            <button class="btn btn-primary waves-effect m-l-15" type="submit">保存</button>
                            <button class="btn btn-info waves-effect m-l-15" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp"%>

<script>
    $('.menu .list .skill').addClass('active')
    addValidatorRules('.form-validation')

    const $addFormBox = $('#add-form-box')
    const $addForm = $addFormBox.find('form')
    const $img = $addForm.find('.fileinput .thumbnail img')

    function add() {
        $addFormBox.modal()
        // 重置表单内容
        $addForm[0].reset()
        // 重新设置图片
        $img.attr('src','${ctx}/asset/admin/img/noimage.png')
    }

    function edit(JSON) {
        add()
        for (const k in JSON){
            const $input = $addForm.find('[name=' + k + ']')
            if ($input.attr('type') === 'file') continue
            $input.val(JSON[k])
        }

        if (JSON.image) $img.attr('src','${ctx}/' + JSON.image)
    }

    function remove(id, name) {
        swal({
            title: "你确定？",
            text: '你确定要删除【' + name + '】？',
            icon: 'warning',
            dangerMode: true,
            buttons: {
                cancel: '取消',
                confirm: '确定'
            }
        }).then(willDelete => {
            if (!willDelete) return

            // 点击了确定删除
            window.location.href = "${ctx}/skill/remove?id=" + id;
        })
    }

    function removeAll() {
        swal({
            title: "你确定？",
            text: "你确定要删除所有选中的记录？",
            icon: "warning",
            dangerMode: true,
            buttons: {
                cancel: "取消",
                confirm: "确定"
            }
        }).then(willDelete => {
            if (!willDelete) return

            // 拿到表单 发送请求
            $('#remove-form').submit()
        })
    }

    const $set = $(".table tbody tr input[type=checkbox]")
    const $removeAll = $('.table-responsive .removeAll')
    $('.table thead th input[type=checkbox]').change(function () {
        let checked = $(this).is(":checked")
        if (checked) {
            $set.each(function () {
                $(this).prop("checked", true)
                $(this).parents('tr').addClass("active")
            })
            $removeAll.removeClass('disabled')
            $removeAll.prop('disabled', false)
        } else {
            $set.each(function () {
                $(this).prop("checked", false)
                $(this).parents('tr').removeClass("active")
            })
            $removeAll.addClass('disabled')
            $removeAll.prop('disabled', true)
        }
    })

    $set.change(function () {
        $(this).parents('tr').toggleClass("active")
        if ($('.table tbody tr input[type=checkbox]:checked').length > 0) {
            $removeAll.removeClass('disabled')
            $removeAll.prop('disabled', false)
        } else {
            $removeAll.addClass('disabled')
            $removeAll.prop('disabled', true)
        }
    })
</script>
</body>

</html>
