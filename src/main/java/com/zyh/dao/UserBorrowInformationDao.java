package com.zyh.dao;

import com.zyh.entity.BookBorrowInformation;
import java.util.List;

public interface UserBorrowInformationDao {
    List<BookBorrowInformation> getUserBorrowInformation(BookBorrowInformation book);
}
