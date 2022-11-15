package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			request.getRequestDispatcher("/WEB-INF/jsp/adminlogin.jsp").forward(request, response);
		}
		else if (action != null && action.equals("error")) {
			request.setAttribute("error", "Login error");
			request.getRequestDispatcher("/WEB-INF/jsp/adminlogin.jsp").forward(request, response);
			
		}
		else if (action != null && action.equals("forbidden")) {
			request.logout();
			request.setAttribute("error", "You do not have sufficient rights to insert new books.");
			request.getRequestDispatcher("/WEB-INF/jsp/adminlogin.jsp").forward(request, response);			
		}
		else {
			response.sendRedirect("Bookshop");
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}