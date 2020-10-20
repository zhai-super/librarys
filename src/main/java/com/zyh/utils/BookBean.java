package com.zyh.utils;


import java.util.List;

/*书籍信息包装类*/
public class BookBean<T> {
    private List<T> borrowerInformation;//借阅者个人信息
    private List<T> bookList;//通过查询得到当前借阅书籍的信息
    private boolean isBorrowingAuthority;//是否有借阅权限(历史借阅书本达3本则无权限)
    private int HisBorBooKCount;//总共未归还的书籍数量
    private int HisBorBookName;//未归还的书名
    private int BorDueTime;//借阅书籍到期时间

    public List<T> getBorrowerInformation() {
        return borrowerInformation;
    }

    public void setBorrowerInformation(List<T> borrowerInformation) {
        this.borrowerInformation = borrowerInformation;
    }

    public List<T> getBookList() {
        return bookList;
    }

    public void setBookList(List<T> bookList) {
        this.bookList = bookList;
    }

    public boolean isBorrowingAuthority() {
        return isBorrowingAuthority;
    }

    public void setBorrowingAuthority(boolean borrowingAuthority) {
        isBorrowingAuthority = borrowingAuthority;
    }

    public int getHisBorBooKCount() {
        return HisBorBooKCount;
    }

    public void setHisBorBooKCount(int hisBorBooKCount) {
        HisBorBooKCount = hisBorBooKCount;
    }

    public int getHisBorBookName() {
        return HisBorBookName;
    }

    public void setHisBorBookName(int hisBorBookName) {
        HisBorBookName = hisBorBookName;
    }

    public int getBorDueTime() {
        return BorDueTime;
    }

    public void setBorDueTime(int borDueTime) {
        BorDueTime = borDueTime;
    }
}
