<jsp:useBean id="obj" class="com.singhsude.suggestor.Suggestor"/> 
<%  
out.print(obj.Result(request.getParameter("stmt")));  
%> 