<%@ page import="java.util.HashMap" %>
<%@ page import="model.entity.RealCustomer" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: Dotin school 6
  Date: 3/17/2015
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html dir="rtl">
<head>

    <title>جستجوی مشتری حقیقی</title>
    <link href="http://cdn.jsdelivr.net/webjars/bootstrap/3.2.0/css/bootstrap.min.css"
          th:href="@{/webjars/bootstrap/3.2.0/css/bootstrap.min.css}"
          rel="stylesheet" media="screen"/>

    <script src="http://cdn.jsdelivr.net/webjars/jquery/2.1.1/jquery.min.js"
            th:src="@{/webjars/jquery/2.1.1/jquery.min.js}"></script>

    <link type="text/css" href="style.css" rel="stylesheet">
</head>
<script>
$(function () {
    var currentId = null;
    var currentName = null;
    var currentFamily = null;
    var currentNameOfFather = null;
    var currentNationalCode = null;
    var currentDateOfBirthday = null;

    var OriginalContent = null;
    var newContent = null;
    var columnNumber, headerText;

    $('#customers').hide();
    $('#delete').hide();
    $('#update').hide();
    $('#Box3').hide();

    $("#name").keydown(function () {
        $('#ajaxResponse').text('');
        $('#ajaxResponse1').text('');
        $('#customers > tbody:last > tr:not(:first)').remove();
        $('#customers').hide();
        $('#delete').hide();
        $('#update').hide();
        $('#Box3').hide();
        currentId = null;
        currentName = null;
        currentFamily = null;
        currentNameOfFather = null;
        currentNationalCode = null;
        currentDateOfBirthday = null;
    });

    $("#family").keydown(function () {
        $('#ajaxResponse').text('');
        $('#ajaxResponse1').text('');
        $('#customers > tbody:last > tr:not(:first)').remove();
        $('#customers').hide();
        $('#delete').hide();
        $('#update').hide();
        $('#Box3').hide();
        currentId = null;
        currentName = null;
        currentFamily = null;
        currentNameOfFather = null;
        currentNationalCode = null;
        currentDateOfBirthday = null;
    });

    $("#id").keydown(function () {
        $('#ajaxResponse').text('');
        $('#ajaxResponse1').text('');
        $('#customers > tbody:last > tr:not(:first)').remove();
        $('#customers').hide();
        $('#delete').hide();
        $('#update').hide();
        $('#Box3').hide();
        currentId = null;
        currentName = null;
        currentFamily = null;
        currentNameOfFather = null;
        currentNationalCode = null;
        currentDateOfBirthday = null;
    });

    $("#customerNumber").keydown(function () {
        $('#ajaxResponse').text('');
        $('#ajaxResponse1').text('');
        $('#customers > tbody:last > tr:not(:first)').remove();
        $('#customers').hide();
        $('#delete').hide();
        $('#update').hide();
        $('#Box3').hide();
        currentId = null;
        currentName = null;
        currentFamily = null;
        currentNameOfFather = null;
        currentNationalCode = null;
        currentDateOfBirthday = null;
    });

    $('#search').on('click', function (e) {
        $('#customers > tbody:last > tr:not(:first)').remove();
        var name = $.trim($('#name').val());
        var family = $.trim($('#family').val());
        var id = $.trim($('#id').val());
        var customerNumber = $.trim($('#customerNumber').val());
        currentId = null;
        currentName = null;
        currentFamily = null;
        currentNameOfFather = null;
        currentNationalCode = null;
        currentDateOfBirthday = null;
        $.get('/selectRealCustomer', {
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: JSON.stringify({"name": name, "family": family, "id": id, "customerNumber": customerNumber})
                },
                function (responseText) {
                    if (responseText != "{}") {
                        $('#customers').show();
                        $('#delete').show();
                        $('#update').show();
                        $('#Box3').show();
                        currentId = null;
                        currentName = null;
                        currentFamily = null;
                        currentNameOfFather = null;
                        currentNationalCode = null;
                        currentDateofBirthday = null;
                        customers = jQuery.parseJSON(responseText);
                        $(function () {
                            var index = 1;
                            $.each(customers, function (i, item) {

                                $('#customers tr:last').after('<tr><td>' + index + '</td><td>' + i + '</td><td>' + item.name + '</td><td>' + item.family + '</td><td>' + item.idCardNumber + '</td><td>' + item.nameOfFather + '</td><td>' + item.dateOfBirth + '</td> </tr>');
                                index++;
                                $('#customers tr').not(':first').click(function () {
                                    $('#customers tr').each(function () {
                                        if ($(this).hasClass('Highlight'))
                                            $(this).removeClass('Highlight');
                                    });
                                    $(this).toggleClass('Highlight');
                                    //var row = $(this).find('td:first').text();
                                    currentId = $(this).closest('#customers tr').find('td:eq(1)').text();
                                    currentName = $(this).closest('#customers tr').find('td:eq(2)').text();
                                    currentFamily = $(this).closest('#customers tr').find('td:eq(3)').text();
                                    currentNameOfFather = $(this).closest('#customers tr').find('td:eq(5)').text();
                                    currentNationalCode = $(this).closest('#customers tr').find('td:eq(4)').text();
                                    currentDateofBirthday = $(this).closest('#customers tr').find('td:eq(6)').text();

                                    //$('#ajaxResponse').text(currentId+" "+currentName+" "+currentFamily+" "+currentNationalCode+" "+currentNameOfFather+" "+currentDateofBirthday);

                                    $("td").dblclick(function (e) {
                                        var whichOne = $(this).index();
                                        if (whichOne > 1) {
                                            columnNumber = $(e.target).index() + 1;
                                            //headerText = $('th:nth-child(' + columnNumber + ')').text();
                                            headerText = $('th:nth-child(' + columnNumber + ')').attr('id');
                                            //OriginalContent = $(this).closest('#customers tr').find('td:eq('+cc+')').text();

                                            if (columnNumber == 3) {
                                                OriginalContent = currentName;
                                            }

                                            if (columnNumber == 4) {
                                                OriginalContent = currentFamily;
                                            }

                                            if (columnNumber == 5) {
                                                OriginalContent = currentNationalCode;
                                            }

                                            if (columnNumber == 6) {
                                                OriginalContent = currentNameOfFather;
                                            }

                                            if (columnNumber == 7) {
                                                OriginalContent = currentDateofBirthday;
                                            }

                                            $(this).addClass("cellEditing");
                                            $(this).html("<input type='text' value='" + OriginalContent + "' />");
                                            $(this).children().first().focus();

                                            $(this).children().first().keypress(function (e) {
                                                if (e.which == 13 || e.code == 13) {
                                                    newContent = $(this).val();
                                                    $(this).parent().text(newContent);
                                                    $(this).parent().removeClass("cellEditing");

                                                    if (columnNumber == 3) {
                                                        currentName = newContent;
                                                    }

                                                    if (columnNumber == 4) {
                                                        currentFamily = newContent;
                                                    }

                                                    if (columnNumber == 5) {
                                                        currentNationalCode = newContent;
                                                    }

                                                    if (columnNumber == 6) {
                                                        currentNameOfFather = newContent;
                                                    }

                                                    if (columnNumber == 7) {
                                                        currentDateOfBirthday = newContent;
                                                    }
                                                    //$('#ajaxResponse').text(currentId+" "+currentName+" "+currentFamily+" "+currentNationalCode+" "+currentNameOfFather+" "+currentDateofBirthday);
                                                }
                                            });

                                            $(this).children().first().blur(function () {
                                                $(this).parent().text(OriginalContent);
                                                $(this).parent().removeClass("cellEditing");
                                            });
                                        }
                                        //$('#ajaxResponse').text(currentId+" "+currentName+" "+currentFamily+" "+currentNationalCode+" "+currentNameOfFather+" "+currentDateofBirthday);
                                    });

                                });
                            });
                        });
                    }
                    else $('#ajaxResponse').text('اطلاعاتی با مشخصات فوق موجود نیست!');
                });
    });

    $('#customers tr').first().click(function () {
        $('#customers tr').each(function () {
            if ($(this).hasClass('Highlight'))
                $(this).removeClass('Highlight');
            currentId = null;
        });
    });

    $('#delete').on('click', function (e) {
        $.get('/deleteRealCustomer', {
                    data: currentId
                },
                function (responseText) {
                    if (responseText == "success") {
                        $('#customers tr').each(function () {
                            if ($(this).hasClass('Highlight')) {
                                $(this).remove();
                            }
                            currentId = null;
                            currentName = null;
                            currentFamily = null;
                            currentNameOfFather = null;
                            currentNationalCode = null;
                            currentDateOfBirthday = null;
                            var rowCount = $('#customers tr').length;
                            $('#ajaxResponse1').text('عملیات حذف با موفقیت انجام شد!');
                            if(rowCount==1)
                            {
                                $('#customers').hide();
                                $('#delete').hide();
                                $('#update').hide();
                                //$('#Box3').hide();
                            }
                        });
                    }
                    else $('#ajaxResponse1').text('خطا در عملیات حذف!');
                });
    });

    $('#update').on('click', function (e) {
        if (currentId != null) {
            $.get('/updateRealCustomer', {
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        data: JSON.stringify({"name": currentName, "family": currentFamily, "nationalCode": currentNationalCode, "dateOfBirth": currentDateOfBirthday, "nameOfFather":currentNameOfFather, "customerNumber":currentId})
                    },
                    function (responseText) {
                        if (responseText == "success") {
                            $('#customers tr').each(function () {
                                if ($(this).hasClass('Highlight'))
                                    $(this).removeClass('Highlight');
                            });
                                $('#ajaxResponse1').text('عملیات به روز رسانی با موفقیت انجام شد!');
                                currentId = null;
                                currentName = null;
                                currentFamily = null;
                                currentNameOfFather = null;
                                currentNationalCode = null;
                                currentDateOfBirthday = null;

                        }
                        else $('#ajaxResponse1').text('خطا در عملیات حذف!');
                        newContent = null;
                        headerText = null;
                        currentId = null;
                        currentName = null;
                        currentFamily = null;
                        currentNameOfFather = null;
                        currentNationalCode = null;
                        currentDateOfBirthday = null;
                    });

        } else {
            $('#ajaxResponse1').text('امکان به روزرسانی وجود ندارد!');
        }

    });
});
</script>

<body class="body">

<div id="Box2">
    <div></div>
    <table>
        <tr>
            <th><label>نام: </label></th>
            <th><input type="text" id="name"/></th>

        </tr>

        <tr>
            <th><label>نام خانوادگی: </label></th>
            <th><input type="text" id="family"/></th>
        </tr>

        <tr>
            <th><label>کد ملی:</label></th>
            <th><input type="text" id="id"></th>
        </tr>

        <tr>
            <th><label>شماره مشتری:</label></th>
            <th><input type="text" id="customerNumber"></th>
        </tr>
    </table>
    <br/>
    <table>
        <tr>
            <th><input type="button" id="search" value="جستجو"/></th>
        </tr>
    </table>

</div>

<div id="ajaxResponse"></div>

<div id="Box3">
    <table id="customers" class="selectedTable" border="1">
        <tr>
            <th class="th1">ردیف</th>
            <th class="th1"> شماره حساب</th>
            <th class="th1" id="firstName">نام</th>
            <th class="th1" id="lastName">نام خانوادگی</th>
            <th class="th1" id="idCardNumber">کدملی</th>
            <th class="th1" id="fatherName">نام پدر</th>
            <th class="th1" id="dateOfBirth">تاریخ تولد</th>
        </tr>
    </table>
    <table>
        <tr>
            <th><input type="button" id="delete" value="حذف"/></th>
            <th><input type="button" id="update" value="به روزرسانی"/></th>
            <div id="ajaxResponse1"></div>
        </tr>
    </table>
</div>
</body>
</html>
