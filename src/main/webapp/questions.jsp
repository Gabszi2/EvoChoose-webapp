<%@ page import ="java.util.*" %>
<%@ page import ="logic.JsonParser" %>
<%@ page import ="org.json.simple.JSONArray" %>
<%@ page import ="org.json.simple.JSONObject" %>
<%@ page import ="java.io.*" %>


<!DOCTYPE html>
<html>
<body>
<center>
<h1>
    Select an answer:
</h1>
<%
String kitchen=(String) request.getAttribute("selectedKitchen");
session.setAttribute("kitchen",kitchen);
String path=(String) request.getAttribute("path");
session.setAttribute("questionpath",path);

    JsonParser jsonQuestion=new JsonParser(path,"questions");

        JSONArray array=jsonQuestion.jsonArrayParser();
        int tempIterator = 0;
        Iterator i = array.iterator();

         while (i.hasNext()) {
         JSONObject question = (JSONObject) i.next();
                     String q = (String) question.get("question");
                     out.println("<br>"+q+"<br>");


%>
<center>
    <form method="post" action="GivenAnswer">
        <br>
        <select name="Answer<%= +tempIterator%>" size="1">
            <option value="true">Yes</option>
            <option value="false">No</option>

        </select>

</center>
<%
        tempIterator++;
    }
 %>
 <br><br>
         <input type="submit">
     </form>
     <% int size =jsonQuestion.getSize();
        session.setAttribute("size",size);
     %>
</body>
</html>