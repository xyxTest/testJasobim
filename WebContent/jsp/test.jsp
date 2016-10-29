<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%  
 String importMsg="";  
 if(request.getSession().getAttribute("msg")!=null){  
    importMsg=request.getSession().getAttribute("msg").toString();  
 }  
 request.getSession().setAttribute("msg", "");  
 %> 
<head>
<base href="<%=basePath%>">
<script type="text/javascript" charset="utf-8" src="../js/jquery-1.11.1.js"></script>
     <title>批量导入客户</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <body>
      <div><font color="bule">批量导入客户</font></div>
      <form action="api/test/testUploadFile" method="post" enctype="multipart/form-data" onsubmit="return check();">
         <div style="margin: 30px;"><input id="excel_file" type="file" name="file" accept="xlsx" size="80"/>
         <input name="type" type="hidden" value="6"/>
         <input id="excel_button" type="submit" value="导入Excel"/></div>
         <font id="importMsg" color="red"><%=importMsg%></font><input type="hidden"/>
     </form>
 </body>
 <script type="text/javascript"> 
 
    function check() {  
          var excel_file = $("#excel_file").val();  
          if (excel_file == "" || excel_file.length == 0) {  
              alert("请选择文件路径！");  
              return false;  
          } else {  
             return true;  
          }  
     } 
    
    $(document).ready(function () {  
           var msg="";  
           if($("#importMsg").text()!=null){  
               msg=$("#importMsg").text();  
           }  
           if(msg!=""){  
               alert(msg);  
           }  
    });  
    
			
 </script>

</html>