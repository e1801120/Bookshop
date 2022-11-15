package controller;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class InsertBookServlet
 */
@WebServlet("/InsertBook")
public class InsertBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/ExampleDB")
    private DataSource ds;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/insertbook.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            BookDAO bookdao = new BookDAO(ds);
            String bookname = request.getParameter("bookname");
            String sprice = request.getParameter("price"); // merkkijonona viel‰
            String spages = request.getParameter("pages");
            if (bookname.trim().isEmpty() || sprice.trim().isEmpty() || spages.trim().isEmpty()) {
                request.setAttribute("error", "Fill in all fields!");
            } else {
                sprice = sprice.replace(',', '.'); //jos desimaalipilkku, korvataan pisteell‰
                @SuppressWarnings("deprecation")
				Double price = new Double(sprice);
                short pages = Short.parseShort(spages);
                Book abook = new Book(0, bookname, pages, price);
                bookdao.insertBook(abook);
                request.logout(); //kirjaudutaan ulos saman tien
                response.sendRedirect("Bookshop"); // lis‰ys onnistui, menn‰‰n alkuun
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Problems. Try again later.");
            e.printStackTrace();
        } catch (Exception e2) {
            request.setAttribute("error", "Check price and pages!");
        }

        // t‰nne tullaan jos ongelmia
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/insertbook.jsp");
        rd.forward(request, response);
    }
}