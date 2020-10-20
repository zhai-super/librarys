package com.zyh.service;

import com.zyh.entity.Book;
import com.zyh.utils.PageBean;



public interface UserSeclectBookService {
    PageBean<Book> userSeclectBook(Book book, int currentPage);
}
