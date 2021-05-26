<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!--用于国际化-->
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function checkname(){
            $.ajax({
                url: '<%=basePath%>/testController/testAjax',
                type: 'POST',//post请求
                data: {name: '小明', age: 18},//传递参数
                dataType: 'json',//设置返回数据类型
                // async:true,//默认为true 异步请求
                // cache:true,//默认为true  缓存此界面
                success: function (data) {//data为回调函数
                    alert(data.name);
                },
                error: function () {

                }
            });
        }

        $(document).ready(function () {
            $("#btn_id").click(function () {
                $.ajax({
                    url: '<%=basePath%>/testController/testAjax',
                    type: 'POST',//post请求
                    data: {name: '小明', age: 18},//传递参数
                    dataType: 'json',//设置返回数据类型
                    // async:true,//默认为true 异步请求
                    // cache:true,//默认为true  缓存此界面
                    success: function (data) {//data为回调函数
                        alert(data.name);
                    },
                    error: function () {

                    }
                });
            })
        });
    </script>
</head>
<body>
        <!--会自动配置文件中id="messageSource" 和地址 进行国际化操作-->
        <fmt:message key="resource.welcom"></fmt:message>
        SUCCESS
        <input type="button" id="btn_id" name="btn_name" value="ajax1">
        <input type="button" id="btn_id2" onclick="checkname()" value="ajax2">

        <form action="testController/testUpload" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            描述：<input type="text" name="desc">
            <input type="submit" value="上传" >
        </form>
</body>
</html>
