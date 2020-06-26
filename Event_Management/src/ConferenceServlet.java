import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewconference")
public class ConferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String ID=request.getParameter("conference");
		PrintWriter out=response.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/events","root","");
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head></head>");
			out.println("<body>");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from conference where ID='"+ID+"';");
			while(rs.next())
			{
				String name=rs.getString("ID");
				String about=rs.getString("About");
				String dat=rs.getString("date1");
				
				out.println("<h1 align='center'>"+name+"</h1><br><br>");
				out.println(about);
				out.println("<br>Event Date: "+dat);
			}
			out.println("</body");
			out.println("</html>");
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			out.print("Conference Servlet Error: "+e.getMessage());
		}	
	}
}
