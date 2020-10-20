package com.zyh.entity;

public class Book {
    /*书籍信息实体类*/
    private Integer bookId;//书籍id
    private String bookName;//书名字
    private String bookAuthor;//作者
    private String bookDscribe;//书籍描述
    private Integer bookCount;//书籍数量
    private BookType bookTypeId;//书籍类型id

    public Book() {
    }

    public Book(Integer bookId, String bookName, String bookAuthor, BookType bookTypeId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookTypeId = bookTypeId;
    }

    public String getBookDscribe() {
        return bookDscribe;
    }

    public void setBookDscribe(String bookDscribe) {
        this.bookDscribe = bookDscribe;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public BookType getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(BookType bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookDscribe='" + bookDscribe + '\'' +
                ", bookCount=" + bookCount +
                ", bookTypeId=" + bookTypeId +
                '}';
    }
}
