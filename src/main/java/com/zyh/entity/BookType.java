package com.zyh.entity;

public class BookType {
    private Integer bookTypeId;
    private String bookTypeName;

    public BookType() {
    }

    public BookType(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }
}
