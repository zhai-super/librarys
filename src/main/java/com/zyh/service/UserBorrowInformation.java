package com.zyh.service;

import com.zyh.entity.Book;
import com.zyh.entity.BookBorrowInformation;
import com.zyh.utils.PageBean;

public interface UserBorrowInformation {
    PageBean<BookBorrowInformation> getUserBorrowInformation(BookBorrowInformation book);
}
