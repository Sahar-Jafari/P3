<%@ page contentType="text/html; charset=utf-8" %>
<html dir="rtl">
<head>
    <title>سامانه حساب مشتریان</title>
    <meta http-equiv="Content-Language" content="fa">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" href="style.css" rel="stylesheet">
    <script src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="javaScripts.js"></script>

</head>
<body class="body">
<div id="Box">
    <table>
        <tr>
            <th><label> نوع مشتری: </label></th>
            <th>
                <select id="customers">
                    <option value="real" selected="selected">
                        حقیقی
                    </option>
                    <option value="legal">
                        حقوقی
                    </option>
                </select>
            </th>
        </tr>
        <tr>
            <th><label>عملیات: </label></th>
            <th><select id="operations">
                <option value="add" selected="selected">
                    افزودن مشتری
                </option>
                <option value="search">
                    جستجوی مشتری
                </option>
            </select></th>
        </tr>
    </table>
    <table>

        <tr>
            <th><input type="button" value="انتخاب" onclick="go()"></th>
        </tr>
    </table>
</div>
</body>
</html>
