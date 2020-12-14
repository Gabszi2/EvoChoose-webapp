<%@ page import ="java.util.*" %>
<%@ page import ="logic.JsonParser" %>

<!DOCTYPE html>
<html>
<body>
<center>
<h1>
    Here is the food that we recommend for you:
</h1>
<%
boolean[] answers= (boolean[])request.getAttribute("answers");
int size=(int)request.getAttribute("size");
String path=(String) request.getAttribute("path");
JsonParser jsonAnswer=new JsonParser(path,"answers");

out.println(jsonAnswer.answerParser(answers,size));


%>


</body>
</html>