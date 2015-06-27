package swe4.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {


	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void destroy(){
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		try{
			System.out.println("Processing Request: =>" + req.getParameter("name"));
			String name = req.getParameter("name");
			
			out.println("<html>");
			out.println("<body>");
			out.println("<h1> Response from Servlet: </h1>");
			out.println("<p> Hallo : "+ name + "</p>");
			out.println("</body>");
			out.println("</html>");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
} // PhoneBookServlet
