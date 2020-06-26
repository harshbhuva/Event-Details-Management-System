

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forlogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String us=request.getParameter("tus");
		String pa=request.getParameter("tpa");
		
		if(us.equals("root") || pa.equals("admin"))
		{
			request.getRequestDispatcher("/admin.html").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/error.html").forward(request, response);
		}	
	}
}