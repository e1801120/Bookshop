package model;

import java.io.Serializable;

public class Book implements Serializable {


    private static final long serialVersionUID = 1L;
	
    private int bookid;
    private double price;
    private String bookname;
    private short pages;
    private int quantity = 0;
    
    

    public Book() {
    }
	
    public Book(int bookid, String bookname, short pages, double price) {
        this.bookid = bookid;
        this.price = price;
        this.bookname = bookname;
        this.pages = pages;
        
    }

    public int getBookid() {
        return this.bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBookname() {
        return this.bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public short getPages() {
        return this.pages;
    }

    public void setPages(short pages) {
        this.pages = pages;
    }
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}