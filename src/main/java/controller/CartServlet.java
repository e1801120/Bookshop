package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Book;
import repository.BookDAO;

/**
 * Bookshop home page
 */
@WebServlet("/Cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    @Resource(name="jdbc/ExampleDB")
    private DataSource ds;

    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String action = request.getParameter("action");
    	if(action == null) {
    		request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
    	}
    	else {
    		HttpSession session = request.getSession();
    		
    		if(action.equalsIgnoreCase("add")) {	
    		
    			List<Book> cart = new ArrayList<Book>();
    			
    			if(session.getAttribute("cart") != null) {
    				cart = (List<Book>) session.getAttribute("cart");
    			}
    			
    			try {
    				BookDAO Bookdao = new BookDAO(ds);
    				int id = Integer.parseInt(request.getParameter("id"));
    				Book book2 = Bookdao.getBook(id);
    				int quantity = Integer.parseInt(request.getParameter("quantity"));
    				book2.setQuantity(quantity);
    				System.out.println(book2.toString());
    				cart.add(book2);
    				
    				session.setAttribute("cart", cart);
    				
    				
    				
    			} catch (SQLException e) {
    				
    				e.printStackTrace();
    			}
    			
    			
    			
    		}
    		else if(action.equalsIgnoreCase("clear")) {
    			
    			session.removeAttribute("cart");
    		}
    	}
    	
        request.getRequestDispatcher("Bookshop").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
    }
}