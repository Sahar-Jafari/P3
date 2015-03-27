<%--
  Created by IntelliJ IDEA.
  User: Dotin school 6
  Date: 3/16/2015
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>
    <title>افزودن مشتری حقیقی</title>


    <link href="http://cdn.jsdelivr.net/webjars/bootstrap/3.2.0/css/bootstrap.min.css"
          th:href="@{/webjars/bootstrap/3.2.0/css/bootstrap.min.css}"
          rel="stylesheet" media="screen"/>

    <script src="http://cdn.jsdelivr.net/webjars/jquery/2.1.1/jquery.min.js"
            th:src="@{/webjars/jquery/2.1.1/jquery.min.js}"></script>

    <link type="text/css" href="style.css" rel="stylesheet">

</head>
<script>
    $(function () {
        $('#add').on('click', function (e) {
            var name = $.trim($('#name').val());
            var family = $.trim($('#family').val());
            var fatherName = $.trim($('#fatherName').val());
            var id = $.trim($('#id').val());
            var date = $.trim($('#dateOfBirth').val());

            if (name == '') $('#nameError').text('لطفاً نام را وارد نمایید!');
            if (family == '') $('#familyError').text('لطفاً نام خانوادگی را وارد نمایید!');
            if (fatherName == '') $('#fatherError').text('لطفاً نام پدر را وارد نمایید');
            if (id == '') $('#idError').text('لطفاً کد ملی را وارد نمایید!');
            if (date == '') $('#dateError').text('لطفاً تاریخ تولد را وارد نمایید!');

            $("#name").click(function () {
                $('#nameError').text('');
            });

            $("#fatherName").click(function () {
                $('#fatherError').text('');
            });

            $("#family").click(function () {
                $('#familyError').text('');
            });

            $("#id").click(function () {
                $('#idError').text('');
            });

            $("#dateOfBirth").click(function () {
                $('#dateError').text('');
            });

            if (name != '' && family != '' && fatherName != '' && id != '' && date != '') {
                $.get('/addRealCustomer', {
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            data: JSON.stringify({"name": name, "family": family, "fatherName": fatherName, "id": id, "date": date})
                        },
                        function (responseText) {
                            if (responseText == "success")
                                $('#ajaxResponse').text('عملیات با موفقیت انجام شد.');
                            else if (responseText == "errorId"){

                                $('#idError').text('کد ملی تکراری است!');
                                $('#id').val('');
                                $('#id').focus();
                            }
                                    });
                        }

            });

        });
</script>

<body class="body">
<div id="Box1">
    <div></div>
    <table class="table1">

        <tr>
            <th><label>نام: </label></th>
            <th><input type="text" id="name"/></th>
        </tr>
        <tr>
            <th>
                <div class="error" id="nameError"/>
            </th>
        </tr>


        <tr>
            <th><label>نام خانوادگی: </label></th>
            <th><input type="text" id="family"/></th>
        </tr>
        <tr>
            <th>
                <div class="error" id="familyError"/>
            </th>
        </tr>


        <tr>
            <th><label>کد ملی:</label></th>
            <th><input type="text" id="id"></th>
        </tr>
        <tr>
            <th>
                <div class="error" id="idError"/>
            </th>
        </tr>


        <tr>
            <th><label>نام پدر:</label></th>
            <th><input type="text" id="fatherName"></th>
        </tr>
        <tr>
            <th>
                <div class="error" id="fatherError"/>
            </th>
        </tr>


        <tr>
            <th><label>تاریخ تولد:</label></th>
            <th><input type="text" id="dateOfBirth"></th>
        </tr>
        <tr>
            <th>
                <div class="error" id="dateError"/>
            </th>
        </tr>

    </table>
    <table>
        <tr>
            <th><input type="button" id="add" value="اضافه کن"/></th>
        </tr>
        <tr>
            <th><div id="ajaxResponse"></div></th>
        </tr>
    </table>
</div>

</body>
</html>
