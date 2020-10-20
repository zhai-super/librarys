package com.zyh.service.impl;

import com.zyh.dao.UserSeclectBookDao;
import com.zyh.dao.impl.UserSeclectBookDaoImpl;
import com.zyh.entity.Book;
import com.zyh.utils.PageBean;
import com.zyh.service.UserSeclectBookService;

public class UserSeclectBookServiceImpl implements UserSeclectBookService {
    private UserSeclectBookDao dao;
    @Override

    public PageBean<Book> userSeclectBook(Book book, int currentPage) {
        if (book!=null) {
            dao = new UserSeclectBookDaoImpl();
            PageBean<Book> bean = dao.userSeclectBook(book, currentPage);
            return bean;
        }
        return null;
    }
}
