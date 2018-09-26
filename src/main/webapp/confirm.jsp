<jsp:useBean id="reg" class="com.singhsude.suggestor.Register"/> 
<%  
String name = request.getParameter("name");
String object = request.getParameter("obj");
String type = request.getParameter("type");
String other = request.getParameter("other");
String age = request.getParameter("age");
out.print(reg.Result(name + "\n" + object + "\n" + type + "\n" + other + "\n" + age));  
%> 