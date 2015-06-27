package swe4.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import swe4.dal.Person;
import swe4.dal.PersonDao;
import swe4.dal.PersonDaoJdbc;

@SuppressWarnings("serial")
public class PhoneBookServlet extends HttpServlet {

	private PersonDao personDao;
	
	public void init() throws ServletException{
		super.init();
		DataSource dataSource = (DataSource)getServletContext().getAttribute("dataSource");
		personDao = new PersonDaoJdbc(dataSource);
	}
	
	public void destroy(){
		if(personDao != null){
			try {
				personDao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.destroy();
	}
	// For each incoming request a thread is assigned to the servlet
	  // that invokes the doGet method. Therefore, doGet has to be 
	  // implemented in a thread-safe way. 
	  // This is the case because PersonDaoJdbc is thread-save. The 
	  // DAO in turn is thread-safe because it only depends on the 
	  // thread-safe BasicDataSource.
	  public void doGet(HttpServletRequest req, 
	                                 HttpServletResponse res)
	      throws ServletException, IOException {

	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();

	    try {
	      out.println ("<html>");
	      out.println ("<head><title>Phone Book</title></head>");
	      out.println ("<body>");
	      out.println ("<h1>Phone Book</h1>");
	      
	      if (req.getParameter("list") != null) {

	        out.println("<h3>List of all entries in phone book</h3>");
	        out.println("<table border=1>");
	        out.println("  <tr><th>ID</th><th>First Name</th><th>Last Name"
	            + "</th><th>Address</th><th>Phone Number</th></tr>");

	        for (Person pers : personDao.getAll()) {
	          out.println("  <tr>");
	          out.println(String.format("    <td>%d</td>", pers.getId()));
	          out.println(String.format("    <td>%s</td>", pers.getFirstName()));
	          out.println(String.format("    <td>%s</td>", pers.getLastName()));
	          out.println(String.format("    <td>%s</td>", pers.getAddress()));
	          out.println(String.format("    <td>%s</td>", 
	                                    pers.getPhoneNumber()));
	          out.println("  </tr>");
	        } // for

	        out.println("</table>");
	      }
	      else if (req.getParameter("find") != null) {

	        String lastName = req.getParameter("findLastName");
	        out.println("<h3>Phone book entries where last name like '" + 
	                    lastName + "'</h3>");
	        out.println("<table border=1>");
	        out.println("  <tr><th>ID</th><th>First Name</th><th>Last Name"
	            + "</th><th>Address</th><th>Phone Number</th></tr>");
	        
	        for (Person pers : personDao.get(lastName)) {
	          out.println("  <tr>");
	          out.println(String.format("    <td>%d</td>", pers.getId()));
	          out.println(String.format("    <td>%s</td>", pers.getFirstName()));
	          out.println(String.format("    <td>%s</td>", pers.getLastName()));
	          out.println(String.format("    <td>%s</td>", pers.getAddress()));
	          out.println(String.format("    <td>%s</td>", 
	                                    pers.getPhoneNumber()));
	          out.println("  </tr>");
	        } // while

	        out.println("</table>");
	      }
	      else if (req.getParameter("insert") != null) {

	        String firstName = req.getParameter("insertFirstName");
	        String lastName = req.getParameter("insertLastName");
	        String address = req.getParameter("insertAddress");
	        String phoneNumber = req.getParameter("insertPhoneNumber");
	        
	        Person pers = new Person(firstName, lastName, address, phoneNumber);
	        personDao.store(pers);
	        out.println("<h3>Entry <" + pers + 
	                    "> inserted into phone book</h3>");
	      }
	      else if (req.getParameter("delete") != null) {
	        String idStr = req.getParameter("id");
	        int id = 0;
	        if (idStr != null && idStr.length() > 0) id = Integer.parseInt(idStr);

	        personDao.delete(id);
	        out.println("<h3>Entry with ID = " + id + 
	                    " deleted from phone book</h3>");
	      }
	      else {
	       out.println("<p style='color:red;'>ERROR: invalid command; " +
	                    "only List, Find, Insert and Delete allowed</p>");
	      } // else

	      out.println("<br><br>Click Browser's back button " + 
	                  "to return to phone book");

	    }
	    catch (Exception ex) {
	      out.printf("<p style='color:red;'>Exception raised: %s</p>%n", ex);
	     ex.printStackTrace();
	    }
	    finally {
	      if (out != null) {
	        out.println ("</body>");
	        out.println ("</html>");
	     }
	    } // try/catch/finally
	  } // doPost
 
} // PhoneBookServlet

