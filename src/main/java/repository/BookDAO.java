package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Book;

public class BookDAO {
	
    private DataSource ds;
    	
    public BookDAO(DataSource ds) throws SQLException {
        this.ds = ds;
    }

    // Hakee kaikki kirjat tietokannasta (HUOM! m‰‰r‰‰ syyt‰ rajoittaa, jos taulussa paljon rivej‰)
    public List<Book> getBooks() throws SQLException {
        List<Book> books = new ArrayList<Book>();
        String sql = "SELECT bookid, bookname, pages, price FROM book";
        try (Connection conn = ds.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement(sql);
                       ResultSet rs = pstm.executeQuery()){
                while(rs.next()) {
                    Book m = new Book(rs.getInt(1), rs.getString(2), rs.getShort(3), rs.getDouble(4));
                    books.add(m);
                }
            }
        }
        return books;
    }
	
    //Hakee kirjan perusavaimella bookid
    public Book getBook(int bookid) throws SQLException {
        Book abook = null;
        String sql = "SELECT bookid, bookname, pages, price FROM book WHERE bookid=?";
        try (Connection conn = ds.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement(sql)){
                pstm.setInt(1, bookid);
                try(ResultSet rs = pstm.executeQuery()){
                    if (rs.next()) {
                        abook = new Book(rs.getInt(1), rs.getString(2), rs.getShort(3), rs.getDouble(4));
                    }
                }
            }
        }
        return abook;
    }
	
    //Lis‰‰ uuden kirjan tietokantaan. Palauttaa uuden tietueen perusavaimen (bookid) arvon.
    public int insertBook(Book abook) throws SQLException {
        String sql = "INSERT INTO book(bookname, pages, price) VALUES(?, ?, ?)";
        int bookid = 0;
        try (Connection conn = ds.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                pstm.setString(1,  abook.getBookname());
                pstm.setShort(2, abook.getPages());
                pstm.setDouble(3, abook.getPrice());
                pstm.executeUpdate();				
                try(ResultSet rs = pstm.getGeneratedKeys()){
                    if (rs.next()) {
                        bookid = rs.getInt(1);
                    }	
                }					
            }
        }
        return bookid;
    }	
}