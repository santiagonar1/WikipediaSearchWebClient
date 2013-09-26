<%-- 
    Document   : index
    Created on : 22-sep-2013, 10:05:40
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wiki Search Web Service Client</title>
    </head>
    <body>
    <center>
        <div style="padding-top: 112px" >
            <div title="WikiSearch" style="background:url(img/logo.png) no-repeat; background-size:460px 205px; height:205px;width:460px" align="left"></div>
        </div>
        <br>

        <form method="get" action="WikiSearchServlet">
            <br><br><input type="text" required="true" name="keyword" style="width: 600px"/>
            <br><input type="submit" value="Search" name="doSearch" style="margin-top: 20px; font-size: 20px; font-weight: lighter"/>
            <input type="submit" value="I'm Feeling Lucky" name="feelingLucky" style="margin-top: 20px; margin-left: 20px; font-size: 20px; font-weight: lighter"/>
        </form>

    </center>
</body>
</html>
