import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editconference")
public class AdminConServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		String ID=request.getParameter("conference");
		String About=request.getParameter("About");
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/events","root","");
			
			PreparedStatement pst=con.prepareStatement("update conference set About=? where ID=?");
			pst.setString(1, About);
			pst.setString(2, ID);
			pst.execute();
			pst.close();
			con.close();
			
			request.getRequestDispatcher("admin.html").include(request, response);
		
		}
		catch(Exception e)
		{
			out.println("AdminCon Servlet Error:"+e.getMessage());
		}
	}

}
