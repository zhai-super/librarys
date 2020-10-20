package com.zyh.dao;

import com.zyh.entity.BookBorrowInformation;
import com.zyh.utils.PageBean;

public interface UserBorrowInformationDao {
    PageBean<BookBorrowInformation> getUserBorrowInformation(BookBorrowInformation book);
}
