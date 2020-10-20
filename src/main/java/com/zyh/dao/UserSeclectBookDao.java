package com.zyh.dao;

import com.zyh.entity.Book;
import com.zyh.utils.PageBean;

public interface UserSeclectBookDao {
    PageBean<Book> userSeclectBook(Book book, int current);
}
