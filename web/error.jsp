<%-- 
    Document   : error
    Created on : 25-sep-2013, 14:28:13
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>No result found</title>
    </head>
    <body>
    <center>
        <h1>Oops!! ... No results found for your search :(</h1>
        <br><br>
        <form method="get" action="WikiSearchServlet">
            <br><input type="submit" value="Back" name="backError" style="margin-top: 20px; font-size: 20px; font-weight: lighter"/>
        </form>
    </center>

</body>
</html>
