<%-- 
    Document   : result
    Created on : 22-sep-2013, 10:28:13
    Author     : santiago
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%!// OJO: no hacer caso del Warning, el operador diamante <> no es soportado!
    private ArrayList<String> result = new ArrayList<String>();
%>

<%
    result = (ArrayList<String>) session.getAttribute("result");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
    </head>
    <body>
    <center>
        <h1>Information obtained</h1>
    </center>
    <br><br><%
        Iterator<String> resultIterator = result.iterator();
        while (resultIterator.hasNext()) {
            String title = resultIterator.next();
            String urlWikipedia = "http://www.wikipedia.org/wiki/" + title;
            String resume = resultIterator.next();
            out.println("<li><a href='" + urlWikipedia + "'>" + title + "</a><br>");
            out.println(resume + "<br><br></li>");
        }
    %>
    <br><br>

    <center>
        <form method="get" action="WikiSearchServlet">
            <br><input type="submit" value="Back" name="back" style="margin-top: 20px; font-size: 20px; font-weight: lighter"/>
        </form>
    </center>

</body>
</html>
