package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Book;
import repository.BookDAO;

/**
 * Bookshop home page
 */
@WebServlet("/Bookshop")
public class BookshopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    @Resource(name="jdbc/ExampleDB")
    private DataSource ds;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BookDAO bookdao = new BookDAO(ds);
            List<Book> books = bookdao.getBooks();
            request.setAttribute("books", books);
            if (request.getParameter("choice") != null) {
                int bookid = Integer.parseInt(request.getParameter("choice"));
                Book abook = bookdao.getBook(bookid);
                request.setAttribute("abook", abook);									
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Problems. Try again later.");
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/books.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        
    }
}