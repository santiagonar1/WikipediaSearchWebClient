<%-- 
    Document   : index
    Created on : 22-sep-2013, 10:04:40
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
        <h1>Wiki Search Web Service Client</h1>
        <br><br>
        This WebApp allows to search for information using a given keyword.
        <form method="get" action="WikiSearchServlet">
            <br><br><input type="text" name="keyword" value=""/>
            <br><input type="submit" value="Search Info" name="doSearch" />
        </form>
    </center>
    </body>
</html>
