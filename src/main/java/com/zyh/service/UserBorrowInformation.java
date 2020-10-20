package com.zyh.service;
import com.zyh.entity.BookBorrowInformation;
import java.util.List;
public interface UserBorrowInformation {
    List<BookBorrowInformation> getUserBorrowInformation(BookBorrowInformation book);
}
